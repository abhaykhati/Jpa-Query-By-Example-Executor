package query.by.example.executor;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import query.by.example.executor.qbe2.City;
import query.by.example.executor.qbe2.CityRepository;
import query.by.example.executor.qbe2.ICityService;

@SpringBootApplication
public class QueryByExampleSpringDataJpaApplication  implements CommandLineRunner{
	
	 private static final Logger log = LoggerFactory.getLogger(QueryByExampleSpringDataJpaApplication.class);
	 
	 @Autowired
	    private ICityService cityService;
	 @Autowired
	    private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(QueryByExampleSpringDataJpaApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
	List<City> asList = Arrays.asList(
		new  City ("Bratislava", 432000),
		new  City ("Budapest", 1759000),
		new  City ("Prague", 1280000),
		new  City ("Warsaw", 1748000),
		new  City ("Los Angeles", 3971000),
		new  City ("New York", 8550000),
		new  City ("Brest", 139163),
		new  City ("Edinburgh", 464000),
		new  City ("Suzhou", 4327066),
		new  City ("Zhengzhou", 4122087),
		new  City ("Berlin", 3671000),
		new  City ("Bucharest", 1836000));
	
	cityRepository.saveAll(asList);
	
	
    log.info("Finding cities by name");
    List<City> res1 = cityService.findByName("B");
           for (City city : res1) {
        	   log.info("City=  "+city.getName());
        	   log.info("population=  "+city.getPopulation());
			
		}
   // log.info(" findByName(\"Bratislava\"){}", res1.forEach(c -> c));

		/*   List<City> res2 = cityService.findByName("Berlin");
		   log.info(" findByName(\"Berlin\"){}", res2);
		
		   log.info("Finding cities by name ending with est");
		   List<City> res3 = cityService.findByNameEnding("est");
		   log.info(" findByNameEnding(\"est\"){}", res3);*/
	
	
	
	}

}
