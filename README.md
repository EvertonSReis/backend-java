# Desafio - Backend JAVA

Olá candidato! 

Te damos as boas vindas para nossa etapa de desafio técnico. Você utilizará esse repositório como base para seu projeto (via Fork). O mesmo já está pronto para receber suas implementações, permitindo você focar nos itens do desafio.

Boa sorte! :-)

## Instruções
- Desenvolver as novas funcionalidades de acordo com as instruções fornecidas pelo RH em arquivo PDF 
- Respeitar os padrões de arquitetura, nomenclatura e frameworks definidos da nossa stack

-------------INFORMAÇÕES DA API--------------------

* Api foi desenvolvida seguindo todos requesitos que foi solicitado no desafio
* Para realização dos testes fazer um build do de VendasApplication localizada no package 'br.com.alterdata.vendas'
* Api com autenticação de segurança, Abrir o Postman e utilizar o mapeamento local com final '/usuarios/login' e no body inserir os dados de email e usuario informados abaixo para que seja gerado um token para autenticação do usuario:

Usuario ADMINISTRADOR login:
* email: supervisor@alterdata.com.br
* senha: 123456

Usuario GERAL login:
* email: usuario@alterdata.com.br
* senha: 123456

Obs: Login ou senha inserido errado ou não inserido, retornara o erro 400 e/ 401.

  * Após localizar o token no body do Postman, ir em Authorization e selecionar o tipo ('Type') Api Key, no campo ao lado inserir a Key = Autorization e o Value = Bearer 'inserir token' (Não esquecer de colocar o Bearer antes do token em Value) e já estara com acesso a Api 

Permissões de Usuario:

* ADMINISTRADOR = Inserir novos usuarios, pesquisa de produto utlizando parte do nome da categoria, atualizar a Role de usuarios, consultar e inserir usuarios e produtos, edição do seu usuario.
* GERAL = Não pode cadastrar novos usuarios, editar apenas seu usuario, não pode alterar a Role, consulta de produtos somente informando nome completo.

Obs: O usuario logado podera atualizar apenas seu usuario.