package tacos.repository;

import tacos.data.Taco;

/**
 * @author Shubhasish
 */
public interface TacoRepository {

    Iterable<Taco> findAll();
    Taco findById(Long id);
    Taco save(Taco taco);
}
