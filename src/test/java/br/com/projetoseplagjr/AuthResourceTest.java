package br.com.projetoseplagjr;

import br.com.projetoseplagjr.controllers.AuthResource;
import br.com.projetoseplagjr.dto.LoginDTO;
import br.com.projetoseplagjr.repository.UserRepository;
import br.com.projetoseplagjr.model.User;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestHTTPEndpoint;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
@TestHTTPEndpoint(AuthResource.class)
public class AuthResourceTest {

    @Inject
    UserRepository userRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        userRepository.deleteAll();
        User user = new User();
        user.setUsername("test");
        user.setPassword("secret");
        userRepository.persist(user);
    }

    @Test
    public void testTokenGenerationAndExpiration() throws InterruptedException {
        LoginDTO dto = new LoginDTO();
        dto.setUsername("test");
        dto.setPassword("secret");
        String token = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/login")
                .then().statusCode(200)
                .body("token", notNullValue())
                .extract().jsonPath().getString("token");

        // token works immediately
        given().header("Authorization", "Bearer " + token)
                .when().get("/pessoas")
                .then().statusCode(200);

        // wait for expiration (configured 1 second in test properties)
        Thread.sleep(1500);

        given().header("Authorization", "Bearer " + token)
                .when().get("/pessoas")
                .then().statusCode(401);
    }
}
