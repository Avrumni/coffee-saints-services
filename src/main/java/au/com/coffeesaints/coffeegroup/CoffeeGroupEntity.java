package au.com.coffeesaints.coffeegroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CoffeeGroupEntity {
    private Integer id;
    private String name;
    private String description;
}
