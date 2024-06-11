package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.steps.OrderSteps;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    private final OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());
    }

    @Test
    @DisplayName("Успешное получение заказов /api/v1/orders")
    @Description("Успешное получение списка заказов")
    public void testOrderList(){
        orderSteps
                .listOrder()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
