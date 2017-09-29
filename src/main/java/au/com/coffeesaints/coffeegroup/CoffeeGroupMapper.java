package au.com.coffeesaints.coffeegroup;

public class CoffeeGroupMapper {
    public static CoffeeGroupEntity map(CoffeeGroupRequest request) {
        return CoffeeGroupEntity.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();
    }

    public static CoffeeGroupResponse map(CoffeeGroupEntity coffeeGroup) {
        return CoffeeGroupResponse.builder()
            .id(coffeeGroup.getId())
            .name(coffeeGroup.getName())
            .description(coffeeGroup.getDescription())
            .build();
    }
}
