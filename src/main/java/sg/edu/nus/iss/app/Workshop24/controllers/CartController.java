package sg.edu.nus.iss.app.Workshop24.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.app.Workshop24.models.LineItem;
import sg.edu.nus.iss.app.Workshop24.models.PurchaseOrder;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession sess) throws Exception{

        List<LineItem> lineItems = (List<LineItem>) sess.getAttribute("cart");
        if (lineItems == null) { // null and empty is different.
            System.out.println("This is a new session");
            System.out.printf("session id = %s\n", sess.getId());
            lineItems = new LinkedList<>();
            sess.setAttribute("cart", lineItems);
        }

        String item = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        lineItems.add(new LineItem(item, quantity));
        PurchaseOrder po = new PurchaseOrder();
        po.setName(form.getFirst("name"));
        for (LineItem li: lineItems) {
            System.out.printf("description: %s, quantity: %d\n", li.getDescription(), li.getQuantity());
        }
        po.setLineItems(lineItems);

        sess.setAttribute("checkoutCart", po);
        model.addAttribute("lineItems", lineItems);

        return "cart_template";
    }
    
}
