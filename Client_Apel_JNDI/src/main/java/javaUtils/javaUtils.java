package javaUtils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class javaUtils {

    public static final String URL = "http://localhost:8080/jndi";



    public static final String ADD_INS_PAGE = "<!DOCTYPE html>\n" +
            "</html>";

    public static final String UPDATE_INS_PAGE = "<!DOCTYPE html>\n" +
            "</html>";

    public static final String DELETE_INS_PAGE = "<!DOCTYPE html>\n" +
            "</html>";


    public static void PageToClient(HttpServletResponse resp, String Page) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(Page);
        out.flush();
        out.close();
    }
}
