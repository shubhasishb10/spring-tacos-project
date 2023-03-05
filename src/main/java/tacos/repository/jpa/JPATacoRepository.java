package tacos.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tacos.data.Taco;


/**
 * @author Shubhasish
 */
@Repository
public interface JPATacoRepository extends PagingAndSortingRepository<Taco, Long> {

    Page<Taco> findAll(Pageable pageable);
}
