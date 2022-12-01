package sg.edu.nus.iss.app.Workshop24.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.app.Workshop24.models.LineItem;
import sg.edu.nus.iss.app.Workshop24.models.PurchaseOrder;
import sg.edu.nus.iss.app.Workshop24.services.OrderService;

@Controller
@RequestMapping(path="/checkout")
public class CheckoutController {
    
    @Autowired
    OrderService ordSvc;

    @PostMapping
    public String postCheckout(Model model, HttpSession sess) throws Exception {
        
        List<LineItem> lineItems = (List<LineItem>) sess.getAttribute("cart");
        PurchaseOrder po = (PurchaseOrder) sess.getAttribute("checkoutCart");
        // Destroy the session
        sess.invalidate();
        ordSvc.createNewOrder(po);
        model.addAttribute("total", lineItems.size());

        return "checkout";
    }
}
