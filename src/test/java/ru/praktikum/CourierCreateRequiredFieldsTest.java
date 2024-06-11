package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.model.Courier;
import ru.praktikum.steps.CourierSteps;

import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class CourierCreateRequiredFieldsTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Courier courier;
    private final String login;
    private final String password;

    public CourierCreateRequiredFieldsTest(String login, String password){
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {null, "123pass"},
                {"Mira", null},
                {null, null}
        };
    }

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());

        courier = new Courier();
        courier.setLogin(login);
        courier.setPassword(password);
    }

    @Test
    @DisplayName("Проверка обязательных полей /api/v1/courier")
    @Description("Проверка обязательных полей создания курьера 400 Bad Request")
    public void testCreateCourierRequiredFields(){
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
}
