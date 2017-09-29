package au.com.coffeesaints.saints.buyer;

import au.com.coffeesaints.saints.SaintEntity;
import au.com.coffeesaints.saints.SaintService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SaintService saintService;

    @GetMapping("/coffee-groups/{coffeeGroupId}/saints/buying")
    public SaintEntity saintBuyer(@PathVariable Integer coffeeGroupId, @RequestParam List<Integer> saintIds) throws Exception {
        return buyerService.findBuyer(coffeeGroupId, saintIds);
    }

    @PostMapping("/coffee-groups/{coffeeGroupId}/saints/{buyerId}/buying")
    public List<SaintEntity> buyingSaint(@PathVariable Integer coffeeGroupId, @PathVariable int buyerId, @RequestBody List<Integer> consumerIds) throws Exception {
        buyerService.buyingSaint(coffeeGroupId, buyerId, consumerIds);
        return saintService.findAllInCoffeeGroupAndInSaintIds(coffeeGroupId, consumerIds);
    }

    @ExceptionHandler(Exception.class)
    public String unknownException(Exception e) {
        log.error("unknownException was: " + e);
        return "It broke because "  + e;
    }
}
