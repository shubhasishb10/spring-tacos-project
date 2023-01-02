package tacos.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.data.Ingredient;
import tacos.data.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static tacos.repository.DomainQueries.QUERY_INSERT_TACO;
import static tacos.repository.DomainQueries.QUERY_INSERT_TACO_INGREDIENTS;
import static tacos.repository.DomainQueries.QUERY_SELECT_TACO;
import static tacos.repository.DomainQueries.QUERY_SELECT_TACO_BY_ID;

/**
 * @author Shubhasish
 */
@Repository
public class JdbcTacoRepository implements TacoRepository {

    private IngredientRepository ingredientRepository;

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc, IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Taco> findAll() {
        return jdbc.query(QUERY_SELECT_TACO, this::mapRowToTaco);
    }

    @Override
    public Taco findById(Long id) {
        return jdbc.queryForObject(QUERY_SELECT_TACO_BY_ID, this::mapRowToTaco, id);
    }

    @Override
    public Taco save(Taco taco) {

        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(Ingredient ingredient : taco.getIngredients()) {
            Ingredient savedIngredient = ingredientRepository.findOne(ingredient.getId());
            saveIngredientToTaco(savedIngredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {

        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(QUERY_INSERT_TACO, Types.VARCHAR, Types.TIMESTAMP);
        // This is needs to be set to true or else key-holder will not have any value
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = factory.newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, Long tacoId) {
        jdbc.update(QUERY_INSERT_TACO_INGREDIENTS, tacoId, ingredient.getId());
    }

    private Taco mapRowToTaco(ResultSet resultSet, int rowNumber) throws SQLException {
        Taco taco = new Taco();
        taco.setId(resultSet.getLong("id"));
        taco.setCreatedAt(resultSet.getTimestamp("createdAt"));
        return taco;
    }
}
