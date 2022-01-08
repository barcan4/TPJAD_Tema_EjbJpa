package Servlets;

import Interfaces.InstrumentService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaUtils.ServletPagesString;

import java.io.IOException;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.InstrumentsToHtml;
import static javaUtils.javaUtils.PageToClient;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
    @EJB
    InstrumentService instrumentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(null) + VIEW_INS_PAGE_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ServletPagesString.ActionPage actionPage = null;
        if (action != null && action.equals("add")) {
            actionPage = ServletPagesString.ActionPage.ADD;
        }
        if (action != null && action.equals("delete")) {
            actionPage = ServletPagesString.ActionPage.DELETE;
        }
        if (action != null && action.equals("update")) {
            actionPage = ServletPagesString.ActionPage.UPDATE;
        }

        if (actionPage != null) {
            switch (actionPage) {
                case ADD:
                    PageToClient(resp, ADD_INS_PAGE);
                    break;
                case DELETE:
                    PageToClient(resp, DELETE_INS_PAGE);
                case UPDATE:
                    PageToClient(resp, UPDATE_INS_PAGE);
                default:
                    break;
            }
        }
        else {
            doGet(req, resp);
        }
    }
}
