package sg.edu.nus.iss.app.Workshop24.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.Workshop24.models.LineItem;
import sg.edu.nus.iss.app.Workshop24.models.PurchaseOrder;

import static sg.edu.nus.iss.app.Workshop24.repositories.Queries.*;

@Repository
public class LineItemRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addLineItems(PurchaseOrder po) {
        addAllLineItems(po.getLineItems(), po.getOrderId());
    }

    public void addAllLineItems(List<LineItem> items, String orderId) {
        List<Object[]> data =  items.stream()
            .map(li -> {
                Object[] l = new Object[3];
                l[0] = li.getDescription();
                l[1] = li.getQuantity();
                l[2] = orderId;
                return l;
            }).toList();

        // Batch update
        jdbcTemplate.batchUpdate(SQL_INSERT_LINE_ITEMS, data);
    
        }
        
}
