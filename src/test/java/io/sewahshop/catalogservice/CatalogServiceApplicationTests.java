package io.sewahshop.catalogservice;

import io.sewahshop.catalogservice.Config.PersistenceConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
@Disabled
@AutoConfigureTestDatabase(
		replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

	@Test
	@Disabled
	void contextLoads() {
	}

}
