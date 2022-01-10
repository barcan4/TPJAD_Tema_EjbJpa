package Servlets;

import Entities.Instrument;
import Entities.Shop;
import Interfaces.ShopService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/addShop")
public class AddShopServlet extends HttpServlet {

    @EJB
    ShopService shopService;

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
