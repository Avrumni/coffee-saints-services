package au.com.coffeesaints.coffeegroup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CoffeeGroupResponse> getCongregations() throws Exception {
        return coffeeGroupService.findAll().stream()
            .map(CoffeeGroupMapper::map)
            .collect(Collectors.toList());
    }

    @PostMapping
    public CoffeeGroupResponse create(@RequestBody CoffeeGroupRequest coffeeGroupRequest) {
        return CoffeeGroupMapper.map(
            coffeeGroupService.create(
                CoffeeGroupMapper.map(coffeeGroupRequest)));
    }
}
