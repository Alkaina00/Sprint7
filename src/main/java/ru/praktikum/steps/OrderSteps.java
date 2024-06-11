package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.model.CreateOrderRequest;

import static io.restassured.RestAssured.given;
import static ru.praktikum.EndPoints.ORDER_CREATE;
import static ru.praktikum.config.RestConfig.HOST;

public class OrderSteps {
    public static final String ORDER_LIST = "/api/v1/orders";

    @Step("Создание заказа /api/v1/orders")
    public ValidatableResponse createOrder(CreateOrderRequest createOrderRequest){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(createOrderRequest)
                .when()
                .post(ORDER_CREATE)
                .then();
    }

    @Step("Получение списка заказов /api/v1/orders")
    public ValidatableResponse listOrder(){
        return given()
                .baseUri(HOST)
                .get(ORDER_LIST)
                .then();
    }
}
