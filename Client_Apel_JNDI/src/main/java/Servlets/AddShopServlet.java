package Servlets;

import Entities.Instrument;
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
import java.util.Properties;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/addShop")
public class AddShopServlet extends HttpServlet {

    private final ShopService shopService;

    public AddShopServlet() throws NamingException {

        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        JNDIProps.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        Context context = new InitialContext(JNDIProps);
        shopService = (ShopService) context.lookup("java:global/Client_Apel_JNDI-1.0-SNAPSHOT/ShopBean!Interfaces.ShopService");}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shop shop = (Shop) req.getSession().getAttribute(SHOP_SESSION_TAG);
        if (shop == null) {
            PageToClient(resp, CHOOSE_SHOP_PAGE + ShopsToHtml(shopService.getShops()) + CHOOSE_SHOP_PAGE_END);
        }
        else {
            String action = req.getParameter("action");
            if (action == null) {
                PageToClient(resp, ADD_SHOP_INS_PAGE + UnownedInsToHtml(shopService.getFreeIns()) + ADD_SHOP_INS_PAGE_END);
            }
            else {
                if (action.equals("back")) {
                    PageToClient(resp, VIEW_SHOP_INS_PAGE + ShopInstrumentsToHtml(shop.getInstruments()) + VIEW_SHOP_INS_PAGE_END);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shop shop = (Shop) req.getSession().getAttribute(SHOP_SESSION_TAG);
        String action = req.getParameter("action");
        if (action == null) {
            PageToClient(resp, ADD_SHOP_INS_PAGE + UnownedInsToHtml(shopService.getFreeIns()) + ADD_SHOP_INS_PAGE_END);
        }
        else {
            if (action.equals("back")) {
                PageToClient(resp, VIEW_SHOP_INS_PAGE + ShopInstrumentsToHtml(shop.getInstruments()) + VIEW_SHOP_INS_PAGE_END);
            }
            if (action.equals("assign")) {
                long idInsTOAdd = Long.parseLong(req.getParameter(INSTRUMENT_ID_REQ_PARM));
                Instrument ins = shopService.findIns(idInsTOAdd);
                shop = shopService.addInsToShop(ins, shop);
                req.getSession().setAttribute(SHOP_SESSION_TAG, shop);
                PageToClient(resp, ADD_SHOP_INS_PAGE + UnownedInsToHtml(shopService.getFreeIns()) + ADD_SHOP_INS_PAGE_END);
            }
        }
    }
}
