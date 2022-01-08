package Servlets;

import Entities.Instrument;
import Interfaces.InstrumentService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static javaUtils.javaUtils.*;
import static javaUtils.ServletPagesString.*;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @EJB
    InstrumentService instrumentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO assert some shop and when sending response to send also the shops instruments
        Instrument instrument = getReqInstrumentEntity(req);
        if (instrument != null) {
            //TODO set instrument belongs to which shop
            instrumentService.addIns(instrument);
            //TODO set shop to include the new instrument
            req.getSession().setAttribute("", "");
        }
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(null) + VIEW_INS_PAGE_END);
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
