package Servlets;

import Entities.Shop;
import Interfaces.ShopService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/main")
public class ChooseShopServlet extends HttpServlet {

    @EJB
    ShopService shopService;

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
