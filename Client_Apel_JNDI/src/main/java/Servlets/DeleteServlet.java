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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private final InstrumentService instrumentService;

    public DeleteServlet() throws NamingException {
        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
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
            instrumentService.deleteIns(instrument);
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
}
