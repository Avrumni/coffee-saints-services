package au.com.coffeesaints.saints;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaintRequest {
    @NotNull
    private String name;
}
