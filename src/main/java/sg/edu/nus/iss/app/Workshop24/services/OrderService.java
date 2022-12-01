package sg.edu.nus.iss.app.Workshop24.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.app.Workshop24.exceptions.OrderException;
import sg.edu.nus.iss.app.Workshop24.models.PurchaseOrder;
import sg.edu.nus.iss.app.Workshop24.repositories.LineItemRepository;
import sg.edu.nus.iss.app.Workshop24.repositories.PurchaseOrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository liRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(PurchaseOrder po) throws OrderException {

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0,8);
        System.out.printf(">>>> OrderId: %s\n", orderId);

        po.setOrderId(orderId);

        // Create the purchaseOrder
        poRepo.insertPurchaseOrder(po);
        System.out.printf(">>>> order quantity: %s\n", po.getLineItems().size());
        if (po.getLineItems().size() > 5) {
            throw new OrderException("Cannot order more than 5 items");
        }
        // Create the associated line items
        liRepo.addAllLineItems(po.getLineItems(), orderId);
    }

}
