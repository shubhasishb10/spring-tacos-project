package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.data.Ingredient;

/**
 * @author Shubhasish
 */
@Repository
public interface JPAIngredientRepository extends CrudRepository<Ingredient, String> {
}
