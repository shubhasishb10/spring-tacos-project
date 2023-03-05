package tacos.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.security.domain.DomainUser;

/**
 * @author Shubhasish
 */
@Repository
public interface UserRepository extends CrudRepository<DomainUser, Long> {

    DomainUser findByUsername(String username);
}
