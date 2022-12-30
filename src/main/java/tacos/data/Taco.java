package tacos.data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Shubhasish
 */
public class Taco {

    private Long id;

    private Date createdAt;
    @NotNull
    @Size(min = 3, message = "Name must be at least 3 character long")
    private String name;
    @Size(min = 1, message = "You must choose at least 1 ingredients")
    private List<String> ingredients = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taco)) return false;
        Taco taco = (Taco) o;
        return getId().equals(taco.getId());
        //return getName().equals(taco.getName()) && getIngredients().equals(taco.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
