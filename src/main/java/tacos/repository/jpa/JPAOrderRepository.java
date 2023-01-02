package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.data.Order;

/**
 * @author Shubhasish
 */
@Repository
public interface JPAOrderRepository extends CrudRepository<Order, Long> {
}
