import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class ReqresTests {

    //пример с занятия 1
    @Test
    void sucsessfulLogin() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    //пример с занятия 2
    @Test
    void negativeLogin() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

    @Test
    void checkListResourses (){
        get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("total_pages", is(2));

    }

    @Test
    void checkSingleUsers (){
        get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is ("Weaver"));

    }

    @Test
    void checkCreate () {
        given()
                .contentType(ContentType.JSON)
                .body("{\n"+"\"name\":\"morpheus\",\n"+"\"job\":\"leader\"\n"+"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", is("leader"));
    }

    @Test
    void checksingleUserNotFound(){
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);

    }

    @Test
    void checkDelete(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
}
