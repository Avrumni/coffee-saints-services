package au.com.coffeesaints.coffeegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeGroupService {
    private final CoffeeGroupDao coffeeGroupDao;

    @Autowired
    public CoffeeGroupService(CoffeeGroupDao coffeeGroupDao) {
        this.coffeeGroupDao = coffeeGroupDao;
    }

    public List<CoffeeGroupEntity> findAll(){
        return coffeeGroupDao.findAll();
    }

    public CoffeeGroupEntity create(CoffeeGroupEntity coffeeGroupEntity){
        return coffeeGroupDao.create(coffeeGroupEntity);
    }

}
