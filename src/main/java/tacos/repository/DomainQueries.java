package tacos.repository;

/**
 * @author Shubhasish
 */
public interface DomainQueries {

    /* Queries for Ingredients */
    String QUERY_SELECT_INGREDIENT = "select id, name, type from Ingredient";
    String QUERY_SELECT_INGREDIENT_BY_ID = "select id, name, type from Ingredient where id=?";
    String QUERY_INSERT_INGREDIENT = "insert into Ingredient (id, name, type) values (?,?,?)";

    /* Queries for tacos */
    String QUERY_INSERT_TACO_INGREDIENTS = "insert into Taco_Ingredients (taco, ingredient) values ( ?,? )";
    String QUERY_INSERT_TACO = "insert into Taco (name, createdAt) values (?, ?)";
    String QUERY_SELECT_TACO = "select id, createdAt from Taco";
    String QUERY_SELECT_TACO_BY_ID = "select id, createdAt from Taco where id=?";
}
