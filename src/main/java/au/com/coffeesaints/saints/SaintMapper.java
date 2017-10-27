package au.com.coffeesaints.saints;

public class SaintMapper {
    public static SaintEntity map(SaintRequest saintRequest) {
        return SaintEntity.builder()
            .name(saintRequest.getName())
            .build();
    }

    public static SaintResponse map(SaintEntity saint) {
        return SaintResponse.builder()
            .id(saint.getId())
            .name(saint.getName())
            .coffeeGroupId(saint.getCoffeeGroupId())
            .coffeeBought(saint.getCoffeeBought())
            .coffeeConsumed(saint.getCoffeeConsumed())
            .coffeeOffset(saint.getCoffeeOffset())
            .build();
    }
}
