package au.com.coffeesaints.saints;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j
public class SaintsController {
    @Autowired
    private SaintService saintService;

    @GetMapping("/saints")
    public List<SaintEntity> getSaints() {
        return saintService.findAll();
    }

    @PostMapping("/saints")
    public SaintEntity getSaints(@RequestBody SaintEntity saintEntity) {
        return saintService.add(saintEntity);
    }
}
