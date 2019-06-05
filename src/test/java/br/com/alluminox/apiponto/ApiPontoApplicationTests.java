package br.com.alluminox.apiponto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
// @ActiveProfiles("test") // | application-test.properies
public class ApiPontoApplicationTests {
	
	@Test
	public void contextLoads() {}
	
}

// java -jar -Dspring.profiles.active=test api.jar | application-tets.properties