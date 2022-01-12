package javaUtils;

import Entities.Instrument;
import Entities.Shop;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import static javaUtils.ServletPagesString.*;

public class javaUtils {

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
            output.append("\t\t\t<input type=\"hidden\" name=\"action\" value=\"update\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"").append(instrument.getIdInstrument()).append("\">\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_NAME_REQ_PARM + "\" value=\"").append(instrument.getName()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_TYPE_REQ_PARM + "\" value=\"").append(instrument.getType()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_PRICE_REQ_PARM + "\" value=\"").append(instrument.getPrice()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"submit\" name=\"updateBtn\" value=\"Update\"></td>\n");
            output.append("\t\t</form>\n");
            output.append("\t\t<form action=\"dispatcher\" method=\"POST\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"action\" value=\"delete\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"").append(instrument.getIdInstrument()).append("\">\n");
            output.append("\t\t\t<td><input type=\"submit\" name=\"deleteBtn\" value=\"Delete\"></td>\n");
            output.append("\t\t</form>");
            output.append("\t<tr>\n");
        });
        return output.toString();
    }

    public static String InsDelToHtml(Instrument instrument) {
        return "<form method=\"POST\" action=\"delete\">\n" +
                "\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"" + instrument.getIdInstrument() + "\">\n" +
                "\tAre you sure you want to delete this entry?<br>\n" +
                "\t<input type=\"submit\" name=\"deleteBtn\" value=\"Delete\">\n" +
                "</form>\n";
    }

    public static String InsUpdToHtml(Instrument instrument) {
        return "<form method=\"POST\" action=\"update\">\n" +
                "\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"" + instrument.getIdInstrument() + "\">\n" +
                "\t<input type=\"\" name=\"" + INSTRUMENT_NAME_REQ_PARM + "\" value=\"" + instrument.getName() + "\" >\n" +
                "\t<input type=\"\" name=\"" + INSTRUMENT_TYPE_REQ_PARM + "\" value=\"" + instrument.getType() + "\" >\n" +
                "\t<input type=\"\" name=\"" + INSTRUMENT_PRICE_REQ_PARM + "\" value=\"" + instrument.getPrice() + "\" >\n" +
                "\t<input type=\"submit\" name=\"updateBtn\" value=\"Update\">\n" +
                "</form>\n";
    }

    public static String ShopInstrumentsToHtml(Collection<Instrument> instrumentCollection) {
        if (instrumentCollection == null) {
            return "";
        }
        StringBuilder output = new StringBuilder();

        instrumentCollection.forEach(instrument -> {
            output.append("\t<tr>\n");
            output.append("\t\t<form action=\"dispatcherShop\" method=\"POST\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"action\" value=\"delete\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"").append(instrument.getIdInstrument()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_NAME_REQ_PARM + "\" value=\"").append(instrument.getName()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_TYPE_REQ_PARM + "\" value=\"").append(instrument.getType()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_PRICE_REQ_PARM + "\" value=\"").append(instrument.getPrice()).append("\"></td>\n");
            output.append("\t\t\t<td><input type=\"submit\" name=\"removeBtn\" value=\"Remove\"></td>\n");
            output.append("\t\t</form>\n");
            output.append("\t</tr>\n");
        });

        return output.toString();
    }

    public static String UnownedInsToHtml(Collection<Instrument> instruments) {
        if (instruments == null) {
            return "";
        }
        StringBuilder output = new StringBuilder();

        instruments.forEach(instrument -> {
            output.append("\t<tr>\n");
            output.append("\t\t<form method=\"POST\" action=\"addShop\">");
            output.append("\t\t\t<input type=\"hidden\" name=\"action\" value=\"assign\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + INSTRUMENT_ID_REQ_PARM + "\" value=\"").append(instrument.getIdInstrument()).append("\">\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_NAME_REQ_PARM + "\" value=\"").append(instrument.getName()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_TYPE_REQ_PARM + "\" value=\"").append(instrument.getType()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + INSTRUMENT_PRICE_REQ_PARM + "\" value=\"").append(instrument.getPrice()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"submit\" name=\"addShopBtn\" value=\"Add to Shop\"></td>");
            output.append("\t\t</form>");
            output.append("\t<tr>\n");
        });

        return output.toString();
    }

    public static String ShopsToHtml(Collection<Shop> shopCollection) {
        if (shopCollection == null) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        shopCollection.forEach(shop -> {
            output.append("\t<tr>\n");
            output.append("\t\t<form action=\"main\" method=\"POST\">\n");
            output.append("\t\t\t<input type=\"hidden\" name=\"" + SHOP_ID_REQ_PARM + "\" value=\"").append(shop.getIdShop()).append("\">\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + SHOP_NAME_REQ_PARM + "\" value=\"").append(shop.getName()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + SHOP_CITY_REQ_PARM + "\" value=\"").append(shop.getCity()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"text\" name=\"" + SHOP_STREET_REQ_PARM + "\" value=\"").append(shop.getStreet()).append("\" readonly></td>\n");
            output.append("\t\t\t<td><input type=\"submit\" name=\"chooseBtn\" value=\"Choose\"></td>\n");
            output.append("\t\t</form>\n");
            output.append("\t<tr>\n");
        });
        return output.toString();

    }
}
