package au.com.coffeesaints.saints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaintService {
    @Autowired
    private SaintDao saintDao;

    public List<SaintEntity> findAll() {
        return saintDao.findAll();
    }

    public List<SaintEntity> findIn(List<Integer> saintIds) {
        return saintDao.findIn(saintIds);
    }

    public SaintEntity add(SaintEntity saintEntity) {
        return saintDao.add(saintEntity);
    }

    public List<SaintEntity> update(List<SaintEntity> saints) throws Exception {
        return saintDao.update(saints);
    }
}
