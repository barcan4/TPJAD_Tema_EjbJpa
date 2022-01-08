package javaUtils;

public class ServletPagesString {
    public enum ActionPage {
        ADD,
        UPDATE,
        DELETE
    };

    public static final String SHOP_ID_REQ_PARM = "shop_id";

    public static final String INSTRUMENT_ID_REQ_PARM = "ins_id";

    public static final String INSTRUMENT_NAME_REQ_PARM = "ins_name";

    public static final String INSTRUMENT_TYPE_REQ_PARM = "ins_type";

    public static final String INSTRUMENT_PRICE_REQ_PARM = "ins_price";

    public static final String ADD_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>" + "Add Page" + "</title>\n" +
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
            "\t<title>" + "Update Page" + "</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "</body>\n" +
            "</html>";

    public static final String DELETE_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>" + "Delete Page" + "</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form method=\"POST\" action=\"delete\">\n" +
            "\t<input type=\"hidden\" name=\"id\" value=\"" + INSTRUMENT_ID_REQ_PARM + "\">\n" +
            "\tAre you sure you want to delete this entry?<br>\n" +
            "\t<input type=\"submit\" name=\"deleteBtn\" value=\"Delete\">\n" +
            "</form>\n" +
            "<form method=\"GET\" action=\"delete\">\n" +
            "\t<input type=\"submit\" value=\"Cancel\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static final String VIEW_INS_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>" + "View Page" + "</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "\t<tr>\n" +
            "\t\t<th>Name</th>\n" +
            "\t\t<th>Type</th>\n" +
            "\t\t<th>Price</th>\n" +
            "\t<tr>\n";
            //TODO de facut tabelul pentru fiecare shop

    public static final String VIEW_INS_PAGE_END = "</table>\n" +
            "<form action=\"dispatcher\" method=\"POST\">\n" +
            "\t<input type=\"submit\" name=\"addBtn\" value=\"Add an Instrument\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";
}
