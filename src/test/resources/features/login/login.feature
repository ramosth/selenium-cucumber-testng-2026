# language: pt
@login
Funcionalidade: Login
  Como cliente da Swag Labs
  Quero entrar no sistema com o meu usuário e senha
  Para acessar o catálogo de produtos e fazer minhas compras

  Cenário: CT01 - Login com usuário e senha válidos
    Dado que estou na página de login
    Quando faço login com o usuário "standard_user" e a senha "secret_sauce"
    Então devo ver a página de produtos