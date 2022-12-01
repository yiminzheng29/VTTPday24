package sg.edu.nus.iss.app.Workshop24.repositories;

public class Queries {
    public static String SQL_INSERT_PURCHASE_ORDER = """
            insert into purchase_order(order_id, name, order_date)
            values
            (?,?,SYSDATE())
            """;
    
    public static String SQL_INSERT_LINE_ITEMS = """
            insert into line_item (description, quantity, order_id)
            values
            (?, ?, ?)
            """;
}
