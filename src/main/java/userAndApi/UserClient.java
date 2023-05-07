package userAndApi;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends ScooterRentSpec {

    private static final String PATH = "api/auth/register";
    private static final String LOGIN_PATH = "api/auth/login";
    private static final String UPDATE="api/auth/user";

    public UserClient() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * создаем user
     */
    public static ValidatableResponse create(User user) {
        return given()
                .spec(ScooterRentSpec.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(PATH)
                .then();
    }

    /**
     * авторизуем usera в системе
     */
    public static ValidatableResponse login(User user) {
        return given()
                .spec(ScooterRentSpec.requestSpecification())
                .body(user)
                .post(LOGIN_PATH)
                .then();
    }

    public static ValidatableResponse userDelete(String token) {
        return given()
                .header("Authorization",token)
                .spec(ScooterRentSpec.requestSpecification())
                .delete(UPDATE)
                .then();
    }


}
