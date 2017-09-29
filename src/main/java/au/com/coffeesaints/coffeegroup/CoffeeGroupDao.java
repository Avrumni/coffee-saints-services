package au.com.coffeesaints.coffeegroup;


import au.com.coffeesaints.db.generated.tables.CoffeeGroup;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeGroupDao {
    private final DSLContext dsl;

    private CoffeeGroup coffeeGroup = CoffeeGroup.COFFEE_GROUP;

    @Autowired
    public CoffeeGroupDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    List<CoffeeGroupEntity> findAll() {
        return dsl
            .select()
            .from(coffeeGroup)
            .fetchInto(CoffeeGroupEntity.class);
    }

    CoffeeGroupEntity create(CoffeeGroupEntity congregationEntity){
        return dsl
            .insertInto(coffeeGroup)
            .columns(coffeeGroup.NAME, coffeeGroup.DESCRIPTION)
            .values(congregationEntity.getName(), congregationEntity.getDescription())
            .returning().fetchOne().into(CoffeeGroupEntity.class);
    }

}
