package au.com.coffeesaints.saints;

import static org.jooq.impl.DSL.trueCondition;

import au.com.coffeesaints.db.generated.tables.Saint;
import org.jooq.DSLContext;
import org.jooq.UpdateConditionStep;
import org.jooq.tools.StringUtils;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaintDao {

    @Autowired
    private DSLContext dsl;
    private Saint saint = Saint.SAINT;

    public List<SaintEntity> findAll() {

        return dsl
                .select()
                .from(saint)
                .fetchInto(SaintEntity.class);
    }

    public List<SaintEntity> find(SaintEntity queryObject) {
        Condition conditions = trueCondition();

        if (!StringUtils.isBlank(queryObject.getName()))
            conditions.and(saint.NAME.like("%" + queryObject.getName() + "%"));
        if (queryObject.getId()  != 0){
            conditions.and(saint.ID.eq(queryObject.getId()));
        }

        return dsl
            .select()
            .from(saint)
            .where(conditions)
            .fetchInto(SaintEntity.class);
    }

    public SaintEntity add(SaintEntity saintEntity) {
        return dsl
                .insertInto(saint)
                .columns(saint.NAME, saint.COFFEE_GROUP_ID)
                .values(saintEntity.getName(), saintEntity.getCoffeeGroupId())
                .returning().fetchOne().into(SaintEntity.class);
    }

    public List<SaintEntity> findAllInCoffeeGroupAndInSaintIds(Integer coffeeGroupId, Collection<Integer> saintIds) {
        return dsl
                .select()
                .from(saint)
                .where(saint.ID.in(saintIds))
                    .and(saint.COFFEE_GROUP_ID.eq(coffeeGroupId))
                .fetchInto(SaintEntity.class);

    }

    public List<SaintEntity> update(List<SaintEntity> saintEntities) throws Exception {
        List<UpdateConditionStep> steps = saintEntities.stream().map((saintEntity) ->
                dsl.update(saint)
                    .set(saint.NAME, saintEntity.getName())
                    .set(saint.COFFEE_BOUGHT, saintEntity.getCoffeeBought())
                    .set(saint.COFFEE_CONSUMED, saintEntity.getCoffeeConsumed())
                    .where(saint.ID.eq(saintEntity.getId())))
                .collect(Collectors.toList());

        try {
            dsl.batch(steps).execute();
        } catch (Exception e) {
            // What's wrong?
            throw e;
        }

        return saintEntities;
    }

    public List<SaintEntity> findAllInCoffeeGroup(Integer coffeeGroupId){
        return dsl
            .select()
            .from(saint)
            .where(saint.COFFEE_GROUP_ID.eq(coffeeGroupId))
            .fetchInto(SaintEntity.class);
    }
}
