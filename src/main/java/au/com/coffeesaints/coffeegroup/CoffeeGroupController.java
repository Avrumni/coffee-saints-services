package au.com.coffeesaints.coffeegroup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/coffee-groups")
public class CoffeeGroupController {

    private final CoffeeGroupService coffeeGroupService;

    @Autowired
    public CoffeeGroupController(CoffeeGroupService coffeeGroupService) {
        this.coffeeGroupService = coffeeGroupService;
    }

    @GetMapping
    public List<CoffeeGroupEntity> getCongregations() throws Exception {
        return coffeeGroupService.findAll();
    }

    @PostMapping
    public CoffeeGroupEntity create(@RequestBody CoffeeGroupEntity coffeeGroupEntity) {
        return coffeeGroupService.create(coffeeGroupEntity);
    }
}
