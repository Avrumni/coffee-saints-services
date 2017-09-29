package au.com.coffeesaints.coffeegroup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoffeeGroupResponse {
    private Integer id;
    private String name;
    private String description;
}
