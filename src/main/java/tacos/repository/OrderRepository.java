package tacos.repository;

import tacos.data.Order;

/**
 * @author Shubhasish
 */
public interface OrderRepository {

    Iterable<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
}
