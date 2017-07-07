package au.com.coffeesaints.saints.buyer;

import lombok.Data;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

@Data
public class BuyerRequest {

    @NotNull
    List<Integer> saintsConsumersIds;

    @NotNull
    int buyerId;
}