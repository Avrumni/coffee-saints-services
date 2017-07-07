package au.com.coffeesaints.saints.buyer;

import au.com.coffeesaints.saints.SaintEntity;
import au.com.coffeesaints.saints.SaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuyerService {

    @Autowired
    private SaintService saintService;

    public SaintEntity findBuyer(List<Integer> saintIds) throws Exception {
        log.info("finding buyer for ids '{}'", saintIds);
        return saintService.findIn(saintIds).stream()
                .min(Comparator.comparing(this::calcCoffeeBalance))
                .orElseThrow(() -> new Exception("failed to find buyer"));
    }

    public void buyingSaint(int buyerId, List<Integer> saintIds) throws Exception {
        List<SaintEntity> saints = saintService.findIn(saintIds);
        int coffeeValue = 1;

        saints = saints.stream()
                .peek(saint -> saint.incrementCoffeeConsumed(coffeeValue))
                .peek(saint ->  {
                    if (saint.getId() == buyerId) {
                        saint.incrementCoffeeBought(saintIds.size() * coffeeValue);
                    }
                }).collect(Collectors.toList());

        saintService.update(saints);
    }

    public int calcCoffeeBalance(SaintEntity saintEntity) {
        return  saintEntity.getCoffeeBought() - saintEntity.getCoffeeConsumed();
    }

}
