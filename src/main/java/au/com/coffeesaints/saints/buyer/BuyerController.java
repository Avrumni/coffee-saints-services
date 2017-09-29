package au.com.coffeesaints.saints.buyer;

import au.com.coffeesaints.saints.SaintEntity;
import au.com.coffeesaints.saints.SaintMapper;
import au.com.coffeesaints.saints.SaintResponse;
import au.com.coffeesaints.saints.SaintService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j
public class BuyerController {

    private final BuyerService buyerService;
    private final SaintService saintService;

    @Autowired
    public BuyerController(BuyerService buyerService, SaintService saintService) {
        this.buyerService = buyerService;
        this.saintService = saintService;
    }

    @GetMapping("/coffee-groups/{coffeeGroupId}/saints/buying")
    public SaintResponse saintBuyer(@PathVariable Integer coffeeGroupId, @RequestParam List<Integer> saintIds) throws Exception {
        return SaintMapper.map(buyerService.findBuyer(coffeeGroupId, saintIds));
    }

    @PostMapping("/coffee-groups/{coffeeGroupId}/saints/{buyerId}/buying")
    public List<SaintResponse> buyingSaint(@PathVariable Integer coffeeGroupId, @PathVariable int buyerId, @RequestBody List<Integer> consumerIds) throws Exception {
        buyerService.buyingSaint(coffeeGroupId, buyerId, consumerIds);
        return saintService.findAllInCoffeeGroupAndInSaintIds(coffeeGroupId, consumerIds).stream()
            .map(SaintMapper::map)
            .collect(Collectors.toList());
    }

    @ExceptionHandler(Exception.class)
    public String unknownException(Exception e) {
        log.error("unknownException was: " + e);
        return "It broke because "  + e;
    }
}
