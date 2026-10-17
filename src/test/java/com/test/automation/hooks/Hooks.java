package com.test.automation.hooks;

import org.openqa.selenium.WebDriver;

import com.test.automation.config.ConfigReader;
import com.test.automation.context.TestContext;
import com.test.automation.driver.DriverFactory;
import com.test.automation.utils.Log;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Ações executadas antes e depois de CADA cenário (substitui o BaseTest).
 *
 *   @Before -> abre o navegador na URL da aplicação e guarda no TestContext
 *   @After  -> fecha o navegador
 *
 * Na etapa 3 entram aqui o Extent Report e os prints.
 */
public class Hooks {

    private final TestContext context;

    // O PicoContainer chama este construtor e entrega o TestContext do cenário atual
    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void openBrowser(Scenario scenario) {
        Log.info("Iniciando o cenário: " + scenario.getName());

        WebDriver driver = DriverFactory.createDriver();
        String baseUrl = ConfigReader.get("base.url", "https://www.saucedemo.com/");
        Log.info("Navegando para: " + baseUrl);
        driver.get(baseUrl);

        context.setDriver(driver);
    }

    @After
    public void closeBrowser(Scenario scenario) {
        Log.info("Cenário finalizado: " + scenario.getName() + " | Status: " + scenario.getStatus());

        // Só fecha se o navegador chegou a abrir
        if (context.getDriver() != null) {
            context.getDriver().quit();
        }
    }
}