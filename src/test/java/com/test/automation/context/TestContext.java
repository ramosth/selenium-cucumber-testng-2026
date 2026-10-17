package com.test.automation.context;

import org.openqa.selenium.WebDriver;

/**
 * "Mochila" compartilhada durante UM cenário.
 *
 * O PicoContainer cria um TestContext novo no início de cada cenário e entrega
 * a MESMA instância para todas as classes que pedirem por ele no construtor
 * (Hooks, LoginSteps, CartSteps...). Assim todo mundo usa o mesmo navegador,
 * sem herança (no projeto TestNG isso era feito com "extends BaseTest").
 *
 * Quando o cenário termina, o objeto é descartado; o próximo cenário começa do zero.
 */
public class TestContext {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}