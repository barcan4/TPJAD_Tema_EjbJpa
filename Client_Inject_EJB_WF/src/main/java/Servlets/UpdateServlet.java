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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    @EJB
    InstrumentService instrumentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(instrumentService.getAll()) + VIEW_INS_PAGE_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Instrument instrument = getReqInstrumentEntity(req);
        if (instrument != null) {
            updateReqInstrumentEntity(req, instrument);
        }
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(instrumentService.getAll()) + VIEW_INS_PAGE_END);
    }

    Instrument getReqInstrumentEntity(HttpServletRequest req) {
        String id = req.getParameter(INSTRUMENT_ID_REQ_PARM);
        if (id == null || id.length() == 0) {
            return null;
        }
        return instrumentService.findIns(Long.parseLong(id));
    }

    void updateReqInstrumentEntity(HttpServletRequest req, Instrument instrument) {
        String insName = req.getParameter(INSTRUMENT_NAME_REQ_PARM);
        if (insName == null || insName.length() == 0) {
            return;
        }
        String insType = req.getParameter(INSTRUMENT_TYPE_REQ_PARM);
        if (insType == null || insType.length() == 0) {
            return;
        }
        String price = req.getParameter(INSTRUMENT_PRICE_REQ_PARM);
        if (price == null || price.length() == 0) {
            return;
        }
        instrument.setName(insName);
        instrument.setType(insType);
        instrument.setPrice(Double.parseDouble(price));
        instrumentService.updateIns(instrument);
    }
}
