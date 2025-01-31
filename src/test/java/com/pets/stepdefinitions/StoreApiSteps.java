package com.pets.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;

public class StoreApiSteps {
    private Response response;
    private JSONObject order;
    private int orderId;
    private int petId;

    @Given("que el usuario tiene una orden con {string} y {string}")
    public void que_el_usuario_tiene_una_orden(String id, String petId) {
        this.orderId = Integer.parseInt(id);
        this.petId = Integer.parseInt(petId);

        order = new JSONObject();
        order.put("id", orderId);
        order.put("petId", this.petId);
        order.put("quantity", 1);
        order.put("shipDate", "2025-01-01T12:00:00.000Z");
        order.put("status", "placed");
        order.put("complete", true);
    }

    @When("envia la solicitud para crear la orden")
    public void envia_la_solicitud_para_crear_la_orden() {
        response = given()
                .contentType("application/json")
                .body(order.toString())
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
    }

    @Then("la respuesta debe tener codigo {int}")
    public void la_respuesta_debe_tener_codigo(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("el cuerpo de la respuesta debe contener el {string} y {string}")
    public void el_cuerpo_de_la_respuesta_debe_contener_el_y(String id, String petId) {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        assertEquals(Integer.parseInt(id), jsonResponse.getInt("id"));
        assertEquals(Integer.parseInt(petId), jsonResponse.getInt("petId"));
    }

    @Then("la orden debe estar registrada en la base de datos")
    public void la_orden_debe_estar_registrada_en_la_base_de_datos() {
        response = given()
                .when()
                .get("https://petstore.swagger.io/v2/store/order/" + orderId);
        assertEquals(200, response.getStatusCode(),"La orden no se encontro en la BD de petstore");
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        assertEquals(orderId, jsonResponse.getInt("id"), "La orden no coincide con la esperada");
        System.out.println("La orden fue almacenada correctamente en la BD de Petstore");
    }

    @Given("que el usuario consulta la orden con {string}")
    public void que_el_usuario_consulta_la_orden_con(String id) {
        this.orderId = Integer.parseInt(id);
    }

    @When("envía la solicitud para obtener la orden")
    public void envia_la_solicitud_para_obtener_la_orden() {
        response = given()
                .when()
                .get("https://petstore.swagger.io/v2/store/order/" + orderId);
    }
}