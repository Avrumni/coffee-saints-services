package au.com.coffeesaints.saints;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j
public class SaintsController {
    private final SaintService saintService;

    @Autowired
    public SaintsController(SaintService saintService) {
        this.saintService = saintService;
    }

    @Deprecated
    @GetMapping("/saints")
    public List<SaintResponse> getSaints() {
        return saintService.findAll().stream()
            .map(SaintMapper::map)
            .collect(Collectors.toList());
    }

    @PostMapping("/saints/{saintId}/lash")
    public SaintResponse lashSaint(@PathVariable Integer saintId) throws Exception {
        return SaintMapper.map(saintService.lash(saintId));
    }

    @GetMapping("/coffee-groups/{coffeeGroupId}/saints")
    public List<SaintResponse> getSaintsInCoffeeGroup(@PathVariable Integer coffeeGroupId) {
        return saintService.findAllInCoffeeGroup(coffeeGroupId).stream()
            .map(SaintMapper::map)
            .collect(Collectors.toList());
    }

    @PostMapping("/coffee-groups/{coffeeGroupId}/saints")
    public SaintResponse addSaint(@PathVariable Integer coffeeGroupId, @RequestBody SaintRequest saintRequest) {
        SaintEntity saintEntity = SaintMapper.map(saintRequest);
        saintEntity.setCoffeeGroupId(coffeeGroupId);

        return SaintMapper.map(saintService.add(saintEntity));
    }
}
