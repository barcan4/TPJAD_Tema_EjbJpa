package Servlets;

import Entities.Instrument;
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
import java.util.Properties;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/dispatcherShop")
public class DispatcherShopServlet extends HttpServlet {

    private final ShopService shopService;

    public DispatcherShopServlet() throws NamingException {
        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        JNDIProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        Context context = new InitialContext(JNDIProps);
        shopService = (ShopService) context.lookup("Client_Apel_JNDI_WF-1.0-SNAPSHOT/ShopBean!Interfaces.ShopService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shop shop = (Shop) req.getSession().getAttribute(SHOP_SESSION_TAG);
        if (shop == null) {
            PageToClient(resp, CHOOSE_SHOP_PAGE + CHOOSE_SHOP_PAGE_END);
        }
        else {
            PageToClient(resp, VIEW_SHOP_INS_PAGE + ShopInstrumentsToHtml(shop.getInstruments()) + VIEW_SHOP_INS_PAGE_END);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Shop shop = (Shop) req.getSession().getAttribute(SHOP_SESSION_TAG);
        ActionPage actionPage = null;
        if (action != null && action.equals("add")) {
            actionPage = ActionPage.ADD;
        }
        if (action != null && action.equals("delete")) {
            actionPage = ActionPage.DELETE;
        }

        if (actionPage != null) {
            switch (actionPage) {
                case ADD:
                    PageToClient(resp, ADD_SHOP_INS_PAGE + UnownedInsToHtml(shopService.getFreeIns()) + ADD_SHOP_INS_PAGE_END);
                    break;
                case DELETE:
                    long insId = Long.parseLong(req.getParameter(INSTRUMENT_ID_REQ_PARM));
                    Instrument ins = shopService.findIns(insId);
                    shop = shopService.removeInsFromShop(ins, shop);
                    req.getSession().setAttribute(SHOP_SESSION_TAG, shop);
                    PageToClient(resp, VIEW_SHOP_INS_PAGE + ShopInstrumentsToHtml(shop.getInstruments()) + VIEW_SHOP_INS_PAGE_END);
                    break;
                default:
                    break;
            }
        }
        else {
            doGet(req, resp);
        }
    }
}
