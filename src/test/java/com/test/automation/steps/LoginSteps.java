package com.test.automation.steps;

import org.testng.Assert;

import com.test.automation.context.TestContext;
import com.test.automation.pages.InventoryPage;
import com.test.automation.pages.LoginPage;
import com.test.automation.pages.components.HeaderComponent;
import com.test.automation.utils.Log;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

/**
 * Passos de login e logout.
 *
 * O texto de cada anotação precisa ser IGUAL à frase do .feature (sem a palavra
 * Dado/Quando/Então/E). {string} captura o texto entre aspas e entrega como parâmetro.
 *
 * A palavra-chave não importa para o Cucumber: uma frase escrita com "E" no .feature
 * encontra um método anotado com @Entao, e vice-versa. Usamos a mesma do .feature
 * só para facilitar a leitura.
 *
 * Os passos são globais: qualquer feature pode usá-los (ex.: o carrinho usa
 * "que estou logado com o usuário padrão" e "faço logout").
 */
public class LoginSteps {

    private final TestContext context;
    private LoginPage loginPage;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    // ---------- Pré-condições ----------

    @Dado("que estou na página de login")
    public void queEstouNaPaginaDeLogin() {
        loginPage = new LoginPage(context.getDriver());
        Assert.assertTrue(loginPage.isLoaded(), "A tela de login não foi exibida.");
    }

    /** Atalho usado como pré-condição pelas outras features (carrinho, checkout...). */
    @Dado("que estou logado com o usuário padrão")
    public void queEstouLogadoComOUsuarioPadrao() {
        InventoryPage inventoryPage = new LoginPage(context.getDriver()).loginAsStandardUser();
        Assert.assertTrue(inventoryPage.isLoaded(), "A página de produtos não foi exibida após o login.");
    }

    // ---------- Ações ----------

    @Quando("faço login com o usuário {string} e a senha {string}")
    public void facoLoginComOUsuarioEASenha(String usuario, String senha) {
        Log.info("Login com o usuário: '" + usuario + "'");
        // Cria a página aqui para o passo funcionar em qualquer feature (ex.: depois de um logout)
        loginPage = new LoginPage(context.getDriver());
        loginPage.login(usuario, senha);
    }

    @Quando("faço logout")
    public void facoLogout() {
        LoginPage paginaDeLogin = new HeaderComponent(context.getDriver()).logout();
        Assert.assertTrue(paginaDeLogin.isLoaded(), "Após o logout, deveria voltar para a tela de login.");
    }

    // ---------- Validações: login com sucesso ----------

    @Entao("devo ver a página de produtos")
    public void devoVerAPaginaDeProdutos() {
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Era esperado entrar no sistema, mas a tela 'Products' não foi exibida.");
    }

    // ---------- Validações: login com erro ----------

    @Entao("devo ver a mensagem de erro {string}")
    public void devoVerAMensagemDeErro(String mensagemEsperada) {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Era esperada uma mensagem de erro, mas nenhuma foi exibida.");
        Assert.assertEquals(loginPage.getErrorMessage(), mensagemEsperada,
                "A mensagem de erro exibida é diferente da esperada.");
    }

    @E("devo continuar na página de login")
    public void devoContinuarNaPaginaDeLogin() {
        Assert.assertTrue(loginPage.isOnLoginPage(), "Era esperado continuar na tela de login.");
    }
}