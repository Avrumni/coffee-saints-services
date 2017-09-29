package au.com.coffeesaints.coffeegroup;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
class CoffeeGroupEntity {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String description;
}
