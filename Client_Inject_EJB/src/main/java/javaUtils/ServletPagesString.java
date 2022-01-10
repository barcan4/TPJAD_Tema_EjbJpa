package javaUtils;

public class ServletPagesString {
    public enum ActionPage {
        ADD,
        UPDATE,
        DELETE
    };

    public static final String SHOP_SESSION_TAG = "shop_session";

    public static final String SHOP_ID_REQ_PARM = "shop_id";

    public static final String SHOP_NAME_REQ_PARM = "shop_name";

    public static final String SHOP_CITY_REQ_PARM = "shop_city";

    public static final String SHOP_STREET_REQ_PARM = "shop_street";

    public static final String INSTRUMENT_ID_REQ_PARM = "ins_id";

    public static final String INSTRUMENT_NAME_REQ_PARM = "ins_name";

    public static final String INSTRUMENT_TYPE_REQ_PARM = "ins_type";

    public static final String INSTRUMENT_PRICE_REQ_PARM = "ins_price";

    public static final String ADD_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Add Page</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form method=\"GET\" action=\"add\">\n" +
            "\tName of the Instrument: <input id=\"addName\" type=\"text\" name=\"" + INSTRUMENT_NAME_REQ_PARM + "\"><br>\n" +
            "\tType of the Instrument: <input id=\"addType\" type=\"text\" name=\"" + INSTRUMENT_TYPE_REQ_PARM + "\"><br>\n" +
            "\tPrice of the Instrument: <input id=\"addPrice\" type=\"text\" name=\"" + INSTRUMENT_PRICE_REQ_PARM + "\"><br>\n" +
            "\t<input type=\"submit\" name=\"addBtn\" value=\"Add Instrument\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static final String UPDATE_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Update Page</title>\n" +
            "</head>\n" +
            "<body>\n";

    public static final String UPDATE_INS_PAGE_END = "<form method\"GET\" action=\"update\">\n" +
            "\t<input type=\"submit\" value=\"Cancel\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static final String DELETE_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Delete Page</title>\n" +
            "</head>\n" +
            "<body>\n";

    public static final String DELETE_INS_PAGE_END = "<form method=\"GET\" action=\"delete\">\n" +
            "\t<input type=\"submit\" value=\"Cancel\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static final String VIEW_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>View Page</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "\t<tr>\n" +
            "\t\t<th>Name</th>\n" +
            "\t\t<th>Type</th>\n" +
            "\t\t<th>Price</th>\n" +
            "\t<tr>\n";

    public static final String VIEW_INS_PAGE_END = "</table>\n" +
            "<form action=\"dispatcher\" method=\"POST\">\n" +
            "\t<input type=\"hidden\" name=\"action\" value=\"add\">\n" +
            "\t<input type=\"submit\" name=\"addBtn\" value=\"Add an Instrument\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static final String VIEW_SHOP_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>View Shop Page</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "\t<tr>\n" +
            "\t\t<th>Name</th>\n" +
            "\t\t<th>Type</th>\n" +
            "\t\t<th>Price</th>\n" +
            "\t<tr>\n";

    public static final String VIEW_SHOP_INS_PAGE_END = "</table>\n" +
            "<form action=\"dispatcherShop\" method=\"POST\">\n" +
            "\t<input type=\"hidden\" name=\"action\" value=\"add\">\n" +
            "\t<input type=\"submit\" name=\"addToShopBtn\" value=\"Add to Shop\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static final String ADD_SHOP_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Add Shop Page</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "\t<tr>" +
            "\t\t<th>Name</th>" +
            "\t\t<th>Type</th>" +
            "\t\t<th>Price</th>" +
            "\t</tr>";

    public static final String ADD_SHOP_INS_PAGE_END = "</table>\n" +
            "<form method=\"POST\" action=\"addShop\">" +
            "\t<input type=\"hidden\" name=\"action\" value=\"back\">\n" +
            "\t<input type=\"submit\" name=\"backBtn\" value=\"Back\">\n" +
            "</form>" +
            "</body>\n" +
            "</html>";

    public static final String CHOOSE_SHOP_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Choose Shop Page</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "\t<tr>" +
            "\t\t<th>Name</th>\n" +
            "\t\t<th>City</th>\n" +
            "\t\t<th>Street</th>\n" +
            "\t</tr>";

    public static final String CHOOSE_SHOP_PAGE_END = "</table>\n" +
            "</body>\n" +
            "</html>";
}
