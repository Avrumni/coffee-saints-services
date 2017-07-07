package au.com.coffeesaints.saints;

import au.com.coffeesaints.db.generated.tables.Saint;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.impl.TableImpl;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SaintEntity {
    int id;

    @NotNull
    String name;

    int coffeeBought;

    int coffeeConsumed;

    public int incrementCoffeeConsumed(int amount) {
        this.coffeeConsumed += amount;
        return this.coffeeConsumed;
    }

    public int incrementCoffeeBought(int amount) {
        this.coffeeBought += amount;
        return this.coffeeBought;
    }
}
