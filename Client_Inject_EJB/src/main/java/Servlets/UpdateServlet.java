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

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.InstrumentsToHtml;
import static javaUtils.javaUtils.PageToClient;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    @EJB
    InstrumentService instrumentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO ca la deletePage in caz de cancel
        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(null) + VIEW_INS_PAGE_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO verificare shop
        Instrument instrument = getReqInstrumentEntity(req);

        if (instrument != null) {
            Instrument updatedInstrument = updateReqInstrumentEntity(req, instrument);
            //TODO actualizare lista la shop cu instrumentul
        }

        PageToClient(resp, VIEW_INS_PAGE + InstrumentsToHtml(null) + VIEW_INS_PAGE_END);
    }

    Instrument getReqInstrumentEntity(HttpServletRequest req) {
        String id = req.getParameter(INSTRUMENT_ID_REQ_PARM);
        if (id == null || id.length() == 0) {
            return null;
        }
        return instrumentService.findIns(Long.parseLong(id));
    }

    Instrument updateReqInstrumentEntity(HttpServletRequest req, Instrument instrument) {
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
        instrument.setName(insName);
        instrument.setType(insType);
        instrument.setPrice(Double.parseDouble(price));
        return instrumentService.updateIns(instrument);
    }
}
