@test
Feature: Validar API de Store en PetStore

  Scenario Outline: Creacion de Orden de compra en PetStore
    Given que el usuario tiene una orden con "<id>" y "<petId>"
    When envia la solicitud para crear la orden
    Then la respuesta debe tener codigo 200
    And el cuerpo de la respuesta debe contener el "<id>" y "<petId>"
    And la orden debe estar registrada en la base de datos

    Examples:
      | id   | petId  |
      | 300  | 400    |

  Scenario Outline: Consulta de la Orden de compra en PetStore
    Given que el usuario consulta la orden con "<id>"
    When envía la solicitud para obtener la orden
    Then la respuesta debe tener codigo 200
    And el cuerpo de la respuesta debe contener el "<id>" y "<petId>"

    Examples:
      | id   | petId  |
      | 300  | 400    |
