# assossiado
Descrição: Esta é uma solução back-end para gerenciar sessões de votação de uma cooperativa.
Com esta aplicação é possível:

- Cadastrar/Criar uma nova pauta;

- Abrir/Criar uma sessão de votação a partir de uma pauta criada;

- Receber votos dos associados da cooperativa nas sessões de votação abertas(SIM/NÃO);

- Contabilizar os votos e dar o resultado da votação na pauta. 
  
# Tecnologias
- Java 1.8;
- Spring Boot Framework;  
- Swagger 2 - versão 2.9.2;
- Postgresql versão 42.2.13;
- Junit-jupter.

Com a aplicação rodando localmente na porta 8087, a documentação da API está disponível em http://localhost:8087/associado/swagger-ui.html:

- Existe um exemplo de chamada a uma API externa, que é a Via Cep no arquivo VotoService.Java ;
