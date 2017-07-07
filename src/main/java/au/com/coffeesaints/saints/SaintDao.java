package au.com.coffeesaints.saints;

import au.com.coffeesaints.db.generated.tables.Saint;
import org.jooq.DSLContext;
import org.jooq.UpdateConditionStep;
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
                .select(saint.ID, saint.NAME, saint.COFFEE_BOUGHT, saint.COFFEE_CONSUMED)
                .from(saint)
                .fetchInto(SaintEntity.class);
    }

    public SaintEntity add(SaintEntity saintEntity) {
        return dsl
                .insertInto(saint)
                .columns(saint.NAME)
                .values(saintEntity.getName())
                .returning().fetchOne().into(SaintEntity.class);
    }

    public List<SaintEntity> findIn(Collection<Integer> saintIds) {
        return dsl
                .select(saint.ID, saint.NAME, saint.COFFEE_BOUGHT, saint.COFFEE_CONSUMED)
                .from(saint)
                .where(saint.ID.in(saintIds))
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
}
