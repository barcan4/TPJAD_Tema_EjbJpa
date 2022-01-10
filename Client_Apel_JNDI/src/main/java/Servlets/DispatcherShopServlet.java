package Servlets;

import Entities.Instrument;
import Entities.Shop;
import Interfaces.ShopService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaUtils.ServletPagesString;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/dispatcherShop")
public class DispatcherShopServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private ShopService shopService;

    public DispatcherShopServlet() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        shopService = (ShopService) context.lookup("java:global/Client_Apel_JNDI-1.0-SNAPSHOT/ShopBean!Interfaces.ShopService");
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
        ServletPagesString.ActionPage actionPage = null;
        if (action != null && action.equals("add")) {
            actionPage = ServletPagesString.ActionPage.ADD;
        }
        if (action != null && action.equals("delete")) {
            actionPage = ServletPagesString.ActionPage.DELETE;
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
