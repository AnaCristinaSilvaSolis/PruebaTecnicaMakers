Feature: Products

  Background: Precondicion
    Given El usuario navega a la pagina de inicio de sesion
    And El usuario completa el formulario de login con credenciales de usuario activo
    And El usuario hace clic en el boton de Login

  Scenario: Productos completos en el carrito de compras
    When El usuario agrega todos los productos al carrito
    And El usuario hace clic en el icono del carrito
    Then El usuario visualiza el detalle del carrito