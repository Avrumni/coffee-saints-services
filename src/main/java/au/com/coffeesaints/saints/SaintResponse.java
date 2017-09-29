package au.com.coffeesaints.saints;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaintResponse {
    private Integer id;
    private String name;
    private Integer coffeeGroupId;
    private Integer coffeeBought;
    private Integer coffeeConsumed;
}
