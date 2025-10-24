#Autor: Domingo Saavedra

#ID HDU AZURE: SEC-10

#Fecha creación: 16-10-2025

Feature: Inicio de sesion
	Como cliente del demo web shop
	deseo logearme
	para acumular puntos cuando haga compras

	@Smoke
	Scenario: despliegue modal de login
		Given el usuario se encuentra en el home
		When selecciona link log in
		Then se visualiza modal de inicio de sesion
		And caja de texto para el ingreso del mail
		And caja de texto para el ingreso del password
		And boton iniciar sesion

	@Smoke
	Scenario Outline: Error login
		Given el usuario se encuentra en el home
		And selecciona link log in
		When se ingresa mail "<mail>"
		And se ingresa password "<password>"
		And presiona boton Log in
		Then se visualiza mensaje de error "<error>"

		Examples:
			| mail   | password | error |
			|estantich@gmail.com | 123456 |Login was unsuccessful. Please correct the errors and try again.\nNo customer account found|
			|domingo.saavedra.111@algo.com | 123450 |Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect|

	@Smoke
	Scenario Outline: login exitoso
		Given el usuario se encuentra en el home
		And selecciona link log in
		When se ingresa mail "<mail>"
		And se ingresa password "<password>"
		And presiona boton Log in
		Then se visualiza el home del sitio
		And se visualiza el usuario logeado "<logeado>"
		Examples:
			| mail | password | logeado |
			|ehontavil@gmail.com| 123456 | ehontavil@gmail.com |
			|ehontavil@gmail.com| 123456 | ehontavil@gmail.com |
