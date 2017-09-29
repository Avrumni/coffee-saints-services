package au.com.coffeesaints.coffeegroup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CoffeeGroupRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
