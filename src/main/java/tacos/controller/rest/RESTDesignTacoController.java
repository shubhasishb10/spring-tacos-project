package tacos.controller.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.config.OrderProps;
import tacos.data.Taco;
import tacos.repository.jpa.JPATacoRepository;

import java.util.Optional;

/**
 * @author Shubhasish
 */
@RestController
@RequestMapping("/rest/design")
@CrossOrigin("*")
public class RESTDesignTacoController {

    private final JPATacoRepository tacoRepository;

    private final OrderProps prop;

    //@Autowired
    //EntityLinks entityLinks;

    RESTDesignTacoController(JPATacoRepository tacoRepository, OrderProps prop) {
        this.tacoRepository = tacoRepository;
        this.prop = prop;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {

        PageRequest pageRequest = PageRequest.of(0, prop.getPageSize(), Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id) {
        Optional<Taco> present = tacoRepository.findById(id);
        return present.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

}
