Feature: Login

  Background: Precondicion
    Given El usuario navega a la pagina de inicio de sesion
    

  @Positive
  Scenario: Login exitoso con credenciales válidas
    When El usuario completa el formulario de login con credenciales de usuario activo
    And El usuario hace clic en el boton de Login
    Then El usuario visualiza el logo de Swag Labs

  @Negative
  Scenario: Login fállido con credenciales incorrectas
    When El usuario completa el formulario de login con credenciales de usuario incorrectos
    And El usuario hace clic en el boton de Login
    Then El usuario visualiza el mensaje de error "Epic sadface: Username and password do not match any user in this service"

  @Positive
  Scenario Outline: Campos obligatorios
    When El usuario no ingresa informacion en el campo username "<username>" o en el campo password "<password>"
    And El usuario hace clic en el boton de Login
    Then El usuario visualiza el mensaje "<message>" de error correspondiente
    Examples:
      | username      | password     | message                            |
      | standard_user |              | Epic sadface: Password is required |
      |               | secret_sauce | Epic sadface: Username is required |