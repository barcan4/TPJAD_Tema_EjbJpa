package javaUtils;

import Dtos.InstrumentDto;
import Dtos.ShopDto;
import Entities.Instrument;
import Entities.Shop;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import static javaUtils.ServletPagesString.*;

public class javaUtils {

    private static final String URL = "http://localhost:8080/ejb";

    public static void PageToClient(HttpServletResponse resp, String Page) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(Page);
        out.flush();
        out.close();
    }

    public static String InstrumentsToHtml(Collection<Instrument> instrumentCollection) {
        if (instrumentCollection == null) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        instrumentCollection.forEach(instrument -> {
            output.append("\t<tr>\n");
            output.append("\t\t<form action=\"dispatcher\" method=\"POST\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"action\" value=\"update\"\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"").append(instrument.getIdInstrument()).append("\">\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_NAME_REQ_PARM + "\" value=\"").append(instrument.getName()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_TYPE_REQ_PARM + "\" value=\"").append(instrument.getType()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_PRICE_REQ_PARM + "\" value=\"").append(instrument.getPrice()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"button\" name=\"updateBtn\" value=\"Update\"></td>\n");
            output.append("\t\t</form>\n");
            output.append("\t\t<form action=\"dispatcher\" method=\"POST\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"action\" value=\"delete\"\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"").append(instrument.getIdInstrument()).append("\">\n");
            output.append("\t\t\t<td><input type=\"button\" name=\"deleteBtn\" value=\"Delete\"></td>\n");
            output.append("\t\t</form>");
            output.append("\t<tr>\n");
        });
        return output.toString();
    }

    public static InstrumentDto InsEntityToInsDto(Instrument ins) {
        return ins != null ? new InstrumentDto(ins.getIdInstrument(), ins.getName(), ins.getType(), ins.getPrice()) : null;
    }

    public static ShopDto ShopEntityToShopDto(Shop shop) {
        return shop != null ? new ShopDto(shop.getIdShop(), shop.getName(), shop.getCity(), shop.getStreet()) : null;
    }
}
