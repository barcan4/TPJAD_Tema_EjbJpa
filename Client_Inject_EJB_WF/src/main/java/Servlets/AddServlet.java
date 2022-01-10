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
import static javaUtils.javaUtils.InstrumentsToHtml;
import static javaUtils.javaUtils.PageToClient;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @EJB
    InstrumentService instrumentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Instrument instrument = getReqInstrumentEntity(req);
        if (instrument != null) {
            instrumentService.addIns(instrument);
        }
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(instrumentService.getAll()) + VIEW_INS_PAGE_END);
    }

    Instrument getReqInstrumentEntity(HttpServletRequest req) {
        String insName = req.getParameter(INSTRUMENT_NAME_REQ_PARM);
        if (insName == null || insName.length() == 0) {
            return null;
        }
        String insType = req.getParameter(INSTRUMENT_TYPE_REQ_PARM);
        if (insType == null || insType.length() == 0) {
            return null;
        }
        String price = req.getParameter(INSTRUMENT_PRICE_REQ_PARM);
        if (price == null || price.length() == 0) {
            return null;
        }
        double insPrice = Double.parseDouble(price);
        return new Instrument(insName, insType, insPrice);
    }
}
