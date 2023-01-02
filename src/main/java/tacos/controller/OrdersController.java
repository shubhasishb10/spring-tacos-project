package tacos.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.Order;
import tacos.repository.jpa.JPAOrderRepository;

/**
 * @author Shubhasish
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrdersController {

    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);
    private final JPAOrderRepository orderRepository;

    @Autowired
    public OrdersController(JPAOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {

        model.addAttribute("eligibleStates", Order.EligibleStates.states);
        //model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors result, Model model, SessionStatus sessionStatus) {

        if(result.hasErrors()) {
            model.addAttribute("eligibleStates", Order.EligibleStates.states);
            return "orderForm";
        }
        log.info("Order submitted:" + order);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
