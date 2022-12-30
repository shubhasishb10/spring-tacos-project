package tacos.repository;

import tacos.data.Ingredient;

/**
 * @author Shubhasish
 */
public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
