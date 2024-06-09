package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.model.Courier;

import static io.restassured.RestAssured.given;
import static ru.praktikum.EndPoints.*;
import static ru.praktikum.config.RestConfig.HOST;

public class CourierSteps {
    @Step("Создание курьера /api/v1/courier")
    public ValidatableResponse createCourier(Courier courier){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(courier)
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Логин курьера в системе /api/v1/courier/login")
    public ValidatableResponse loginCourier(Courier courier){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(courier)
                .when()
                .post(LOGIN_COURIER)
                .then();
    }

    @Step("Удаление курьера /api/v1/courier/:id")
    public ValidatableResponse deleteCourier(Courier courier){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .pathParam("id", courier.getId())
                .when()
                .delete(DELETE_COURIER)
                .then();
    }
}
