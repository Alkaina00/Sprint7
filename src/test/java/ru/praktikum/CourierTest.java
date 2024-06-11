package ru.praktikum;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.model.Courier;
import ru.praktikum.steps.CourierSteps;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CourierTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());

        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    @DisplayName("Успешная проверка /api/v1/courier")
    @Description("Успешная проверка создания курьера")
    public void testCreateCourierOk(){
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Проверка создания курьера дубля /api/v1/courier")
    @Description("Проверка создания курьера дубля по логину 409 Сonflict")
    public void testCreateCourierDouble(){
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));

        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Успешная проверка /api/v1/courier")
    @Description("Успешная проверка логина курьера")
    public void testLoginShouldReturnId(){
        courierSteps
                .createCourier(courier);

        courierSteps
                .loginCourier(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @After
    public void tearDown(){
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");

        courier.setId(id);
        courierSteps.deleteCourier(courier);
    }
}
