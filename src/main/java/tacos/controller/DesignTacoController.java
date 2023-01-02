package tacos.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.data.Ingredient;
import tacos.data.Ingredient.Type;
import tacos.data.Order;
import tacos.data.Taco;
import tacos.repository.jpa.JPAIngredientRepository;
import tacos.repository.jpa.JPATacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Shubhasish
 */
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final JPAIngredientRepository ingredientRepository;
    private final JPATacoRepository tacoRepository;

    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    //private static final BiPredicate<Ingredient, Type> FILTER_PREDICATE = ((Ingredient ingredient, Type type) -> ingredient.getType() == type);
    @Autowired
    public DesignTacoController(JPAIngredientRepository ingredientRepository, JPATacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /*private static final List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Floure Tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monetory Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );*/

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        Type[] types = Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(fetchIngredients(), type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco taco, BindingResult errors, Model model, @ModelAttribute Order order) {
        if(errors.hasErrors()) {
            Type[] types = Type.values();
            for(Type type : types) {
                model.addAttribute(type.toString().toLowerCase(), filterByType(fetchIngredients(), type));
            }
            model.addAttribute("ingredients", ((Taco) Objects.requireNonNull(errors.getTarget())).getIngredients());
            return "design";
        }
        log.info("Processing design:" + taco);
        Taco savedTaco = tacoRepository.save(taco);
        order.addDesign(savedTaco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(final List<Ingredient> ingredients, final Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType() == type).collect(Collectors.toList());
    }
    private List<Ingredient> fetchIngredients() {
        List<Ingredient> ingredients = new ArrayList<>(50);
        ingredientRepository.findAll().forEach(ingredients::add);
        return ingredients;
    }
}
