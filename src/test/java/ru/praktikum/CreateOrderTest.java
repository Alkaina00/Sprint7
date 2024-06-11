package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.model.CreateOrderRequest;
import ru.praktikum.steps.OrderSteps;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderSteps orderSteps = new OrderSteps();
    private CreateOrderRequest createOrderRequest;
    private final String[] colors;

    public CreateOrderTest(String[] colors){
        this.colors = colors;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        });
    }

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());

        createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName("Иван");
        createOrderRequest.setLastName("Иванов");
        createOrderRequest.setAddress("г. Москва, ул. Ленина, д.12");
        createOrderRequest.setMetroStation(4);
        createOrderRequest.setPhone("+7 800 355 35 35");
        createOrderRequest.setRentTime(5);
        createOrderRequest.setDeliveryDate("2024-07-07");
        createOrderRequest.setComment("Позвонить за 15 минут перед доставкой");
        createOrderRequest.setColor(colors);
    }

    @Test
    @DisplayName("Успешное создание заказа /api/v1/orders")
    @Description("Проверка успешного создания заказа с разными параметрами цвета")
    public void testCreateOrderTest(){
        orderSteps
                .createOrder(createOrderRequest)
                .statusCode(201)
                .body("track", notNullValue());
    }
}
