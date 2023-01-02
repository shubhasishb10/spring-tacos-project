package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.data.Taco;

/**
 * @author Shubhasish
 */
@Repository
public interface JPATacoRepository extends CrudRepository<Taco, Long> {
}
