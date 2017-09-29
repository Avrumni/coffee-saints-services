package au.com.coffeesaints.saints;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<SaintEntity> getSaints() {
        return saintService.findAll();
    }

    @GetMapping("/coffee-groups/{congregationId}/saints")
    public List<SaintEntity> getSaintsInCongregation(@PathVariable Integer congregationId) {
        return saintService.findAllInCongregation(congregationId);
    }

    @PostMapping("/saints")
    public SaintEntity addSaint(@RequestBody SaintEntity saintEntity) {
        return saintService.add(saintEntity);
    }
}
