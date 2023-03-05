package tacos.repository.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.data.Order;
import tacos.security.domain.DomainUser;

import java.util.List;

/**
 * @author Shubhasish
 */
@Repository
public interface JPAOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByDomainUserOrderByPlacedAtDesc(DomainUser user, Pageable pageable);
}
