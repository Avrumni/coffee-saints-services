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

    @GetMapping("/saints/buying")
    public SaintEntity saintBuyer(@RequestParam List<Integer> saintIds) throws Exception {
        return buyerService.findBuyer(saintIds);
    }

    @PostMapping("/saints/{id}/buying")
    public List<SaintEntity> buyingSaint(@PathVariable int id, @RequestBody BuyerRequest buyerRequest) throws Exception {
        buyerRequest.setBuyerId(id);
        buyerService.buyingSaint(buyerRequest);
        return saintService.findIn(buyerRequest.getSaintsConsumersIds());
    }

    @ExceptionHandler(Exception.class)
    public String unknownException() {
        return "It broke";
    }
}
