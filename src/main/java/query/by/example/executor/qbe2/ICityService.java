package query.by.example.executor.qbe2;

import java.util.List;

public interface ICityService {

    List<City> findByNameEnding(String ending);
    List<City> findByName(String name);
}