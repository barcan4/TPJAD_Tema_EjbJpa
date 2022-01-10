package Servlets;

import Entities.Shop;
import Interfaces.ShopService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        JNDIProps.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        Context context = new InitialContext(JNDIProps);
        shopService = (ShopService) context.lookup("java:global/Client_Apel_JNDI-1.0-SNAPSHOT/ShopBean!Interfaces.ShopService");
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
