package tacos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.config.OrderProps;
import tacos.data.Order;
import tacos.repository.jpa.JPAOrderRepository;
import tacos.security.domain.DomainUser;
import tacos.security.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Shubhasish
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrdersController {

    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);
    private final JPAOrderRepository orderRepository;
    private final UserRepository userRepository; // This is not used but can be used to identify the current user logged in using Principal interface
    private final OrderProps orderProps;

    @Autowired
    public OrdersController(JPAOrderRepository orderRepository, UserRepository userRepository, OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {

        model.addAttribute("eligibleStates", Order.EligibleStates.states);
        log.info("Page size is ====== " + orderProps.getPageSize());
        //model.addAttribute("order", new Order());
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal DomainUser user, Model model) {

        Pageable pageRequest = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByDomainUserOrderByPlacedAtDesc(user, pageRequest));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors result, Model model,
                               SessionStatus sessionStatus,
                               Principal principal, // This method is not used
                               Authentication authentication,
                               @AuthenticationPrincipal DomainUser domainUser
                               ) {

        if (result.hasErrors()) {
            model.addAttribute("eligibleStates", Order.EligibleStates.states);
            return "orderForm";
        }
        // This can be used to get the current logged in user
        //DomainUser domainUser = (DomainUser) authentication.getPrincipal();
        // Can be also done like below, but in this case userRepository will be injected in the controller
        // DomainUser domainUser = userRepository.findByUsername(principal.getName());
        order.setDomainUser(domainUser);
        log.info("Order submitted:" + order);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
