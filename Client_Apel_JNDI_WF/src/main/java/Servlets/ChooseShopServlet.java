package Servlets;

import Entities.Shop;
import Interfaces.ShopService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/main")
public class ChooseShopServlet extends HttpServlet {

    private final ShopService shopService;

    public ChooseShopServlet() throws NamingException {
        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        JNDIProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        Context context = new InitialContext(JNDIProps);
        shopService = (ShopService) context.lookup("Client_Apel_JNDI_WF-1.0-SNAPSHOT/ShopBean!Interfaces.ShopService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Shop> shops = shopService.getShops();
        PageToClient(resp, CHOOSE_SHOP_PAGE + ShopsToHtml(shops) + CHOOSE_SHOP_PAGE_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idShop = Long.parseLong(req.getParameter(SHOP_ID_REQ_PARM));
        Shop shop = shopService.findShop(idShop);
        req.getSession().setAttribute(SHOP_SESSION_TAG, shop);
        PageToClient(resp, VIEW_SHOP_INS_PAGE + ShopInstrumentsToHtml(shop.getInstruments()) + VIEW_SHOP_INS_PAGE_END);
    }
}
