package au.com.coffeesaints.saints.buyer;

import au.com.coffeesaints.saints.SaintEntity;
import au.com.coffeesaints.saints.SaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuyerService {

    @Autowired
    private SaintService saintService;

    public SaintEntity findBuyer(Integer coffeeGroupId, List<Integer> saintIds) throws Exception {
        log.info("finding buyer for ids '{}'", saintIds);
        return saintService.findAllInCoffeeGroupAndInSaintIds(coffeeGroupId, saintIds).stream()
            .min(Comparator.comparing(this::calcCoffeeBalance))
            .orElseThrow(() -> new Exception("failed to find buyer"));
    }

    public void buyingSaint(Integer congregationId, Integer buyerId, List<Integer> consumerIds) throws Exception {
        final int COFFEE_VALUE = 1;
        List<Integer> saints = new ArrayList<>();
        saints.addAll(consumerIds);
        saints.add(buyerId);
        List<SaintEntity> saintEntities = saintService.findAllInCoffeeGroupAndInSaintIds(congregationId, saints);


        Map<Transaction, List<SaintEntity>> isBuyer = saintEntities.stream()
            .collect(Collectors.groupingBy(saint ->
                saint.getId() == buyerId ? Transaction.BUYER : Transaction.CONSUMER));

        if (consumerIds.contains(buyerId)) {
            isBuyer.get(Transaction.CONSUMER).addAll(isBuyer.get(Transaction.BUYER));
        }

        isBuyer.get(Transaction.CONSUMER).forEach(saint -> saint.incrementCoffeeConsumed(COFFEE_VALUE));
        isBuyer.get(Transaction.BUYER).forEach(saint -> saint.incrementCoffeeBought(isBuyer.get(Transaction.CONSUMER).size() * COFFEE_VALUE));

        saintService.update(saintEntities);
    }

    public int calcCoffeeBalance(SaintEntity saintEntity) {
        return saintEntity.getCoffeeBought() - saintEntity.getCoffeeConsumed() - saintEntity.getCoffeeOffset();
    }

   private enum Transaction {
       BUYER,
       CONSUMER
    }
}