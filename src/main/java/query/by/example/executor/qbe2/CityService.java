package query.by.example.executor.qbe2;


import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findByNameEnding(String ending) {

       City city = new City();
        city.setName(ending);

        ExampleMatcher matcher = ExampleMatcher
        		.matching()
                .withMatcher("name", match -> match.endsWith())
                .withIgnorePaths("population");

        Example<City> example = Example.of(city, matcher);
        return (List<City>) cityRepository.findAll(example);
    }

    @Override
    public List<City> findByName(String name) {

        City city = new City();
        city.setName(name);

        ExampleMatcher matcher = ExampleMatcher
				        		.matching()
				        		//import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
				        		.withMatcher("name",startsWith())
				                //.withMatcher("name",exact())
				                .withIgnorePaths("population");

        Example<City> example = Example.of(city, matcher);
        return (List<City>) cityRepository.findAll(example);
    }
}