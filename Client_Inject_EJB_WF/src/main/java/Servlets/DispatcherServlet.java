package Servlets;

import Entities.Instrument;
import Interfaces.InstrumentService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
    @EJB
    InstrumentService instrumentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(instrumentService.getAll()) + VIEW_INS_PAGE_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ActionPage actionPage = null;
        if (action != null && action.equals("add")) {
            actionPage = ActionPage.ADD;
        }
        if (action != null && action.equals("delete")) {
            actionPage = ActionPage.DELETE;
        }
        if (action != null && action.equals("update")) {
            actionPage = ActionPage.UPDATE;
        }
        Instrument instrument;

        if (actionPage != null) {
            switch (actionPage) {
                case ADD:
                    PageToClient(resp, ADD_INS_PAGE);
                    break;
                case DELETE:
                    instrument = instrumentService.findIns(Long.parseLong(req.getParameter(INSTRUMENT_ID_REQ_PARM)));
                    PageToClient(resp, DELETE_INS_PAGE + InsDelToHtml(instrument) + DELETE_INS_PAGE_END);
                    break;
                case UPDATE:
                    instrument = instrumentService.findIns(Long.parseLong(req.getParameter(INSTRUMENT_ID_REQ_PARM)));
                    PageToClient(resp, UPDATE_INS_PAGE + InsUpdToHtml(instrument) + UPDATE_INS_PAGE_END);
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
