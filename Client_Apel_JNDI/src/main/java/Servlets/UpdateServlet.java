package Servlets;

import Entities.Instrument;
import Interfaces.InstrumentService;
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
import static javaUtils.javaUtils.InstrumentsToHtml;
import static javaUtils.javaUtils.PageToClient;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private final InstrumentService instrumentService;

    public UpdateServlet() throws NamingException {
        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        JNDIProps.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        Context context = new InitialContext(JNDIProps);
        instrumentService = (InstrumentService) context.lookup("java:global/Client_Apel_JNDI-1.0-SNAPSHOT/InstrumentBean!Interfaces.InstrumentService");
    }

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
