package Servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import javax.naming.Context;
import java.util.Properties;

@WebServlet("/instruments")
public class InstrumentServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
}
