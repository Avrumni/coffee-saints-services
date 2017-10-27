package au.com.coffeesaints.saints;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaintEntity {
    private Integer id;
    private String name;
    private Integer coffeeBought;
    private Integer coffeeConsumed;
    private Integer coffeeGroupId;
    private Integer coffeeOffset;

    public Integer incrementCoffeeConsumed(Integer amount) {
        this.coffeeConsumed += amount;
        return this.coffeeConsumed;
    }

    public Integer incrementCoffeeBought(Integer amount) {
        this.coffeeBought += amount;
        return this.coffeeBought;
    }

    public Integer incrementCoffeeOffset(Integer amount) {
        this.coffeeOffset += amount;
        return this.coffeeOffset;
    }
}
