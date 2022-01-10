package Servlets;

import Entities.Instrument;
import Interfaces.InstrumentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaUtils.ServletPagesString;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

import static javaUtils.ServletPagesString.*;
import static javaUtils.javaUtils.*;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    private final InstrumentService instrumentService;

    public DispatcherServlet() throws NamingException {
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
