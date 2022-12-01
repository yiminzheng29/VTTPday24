package sg.edu.nus.iss.app.Workshop24.models;

import java.util.List;

public class PurchaseOrder {
    private String orderId;
    private String name;
    private List<LineItem> lineItems;

    public List<LineItem> getLineItems() {
        return lineItems;
    }
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
}

