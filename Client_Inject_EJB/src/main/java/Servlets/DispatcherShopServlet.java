package Servlets;

import Entities.Instrument;
import Entities.Shop;
import Interfaces.ShopService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaUtils.ServletPagesString;

import java.io.IOException;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/dispatcherShop")
public class DispatcherShopServlet extends HttpServlet {

    @EJB
    ShopService shopService;

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
