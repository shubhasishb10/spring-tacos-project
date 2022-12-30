package tacos.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.data.Ingredient;
import tacos.repository.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static tacos.repository.DomainQueries.QUERY_INSERT_INGREDIENT;
import static tacos.repository.DomainQueries.QUERY_SELECT_INGREDIENT;
import static tacos.repository.DomainQueries.QUERY_SELECT_INGREDIENT_BY_ID;

/**
 * @author Shubhasish
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(QUERY_SELECT_INGREDIENT, this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbcTemplate.queryForObject(QUERY_SELECT_INGREDIENT_BY_ID, this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(QUERY_INSERT_INGREDIENT, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNo) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
                                resultSet.getString("name"),
                                Ingredient.Type.valueOf(resultSet.getString("type")));
    }
}
