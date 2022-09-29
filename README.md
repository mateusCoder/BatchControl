# BatchControl 

<div align="center">
  <a href="https://www.java.com/pt-BR/" target="_blank" rel="noreferrer" rel="noopener">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
  </a>
  <a href="https://spring.io/" target="_blank" rel="noreferrer" rel="noopener">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring"/>
  </a>
  <a href="https://git-scm.com/" target="_blank" rel="noreferrer" rel="noopener">
    <img src="https://img.shields.io/badge/Git-E34F26?style=for-the-badge&logo=git&logoColor=white" alt="Git"/>
  </a>
  <a href="https://www.microsoft.com/pt-br/windows/?r=1" target="_blank" rel="noreferrer" rel="noopener">
  <img src="https://img.shields.io/badge/Windows-017AD7?style=for-the-badge&logo=windows&logoColor=white" alt="Windows"/>
  </a>
  <a href="https://www.postgresql.org/" target="_blank" rel="noreferrer" rel="noopener">
  <img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  </a>
</div></br>


> O BatchControl é uma API monolitica para controle de lotes, com ela é possível dar entrada e saída de lotes, tanto para varejo com quantidades avulsas, como também por atacado com lotes fechados. As saidas respeitam o FIFO, ou seja, o primeiro a entrar é o primeiro a sair.


## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Ter instalado a  linguagem `Java JDK 17 LTS`.
* Ter instalado a IDE `IntelliJIDEA` ou `Eclipse Spring`.
* Ter uma máquina `Windows 10` ou `11`.


## 📌 EndPoints
Para acessar os endpoints da Entidade Loja:
```
  GET -  v1/stores                 (Lista todos as lojas cadastradas) 
  GET -  v1/stores/{id}            (Detalha o cadastro de uma lojaexistente pelo ID)
  POST - v1/stores                 (Cadastra uma nova loja) 
  PUT -  v1/stores/{id}            (Atualiza o cadastro de uma loja existente pelo ID) 
```
Para acessar os endpoints da Entidade Products:
```
  GET -  v1/stores/{id}/products               (Lista todos os produtos de uma loja) 
  GET -  v1/stores/{id}/products/{productId}   (Detalha um produto de uma loja pelo ID)
  POST - v1/stores/{id}/products               (Cadastra um novo produto para uma loja) 
  PUT -  v1/stores/{id}/products/{productId}   (Atualiza um produto de uma loja pelo ID) 
```
Para acessar os endpoints da Entidade Batches:
```
  GET -  v1/products/{id}/batches            (Lista os lotes de um produto) 
  POST - v1/products/{id}/batches            (Cadastra a entrada de um novo lote de um produto) 
  PUT -  v1/products/{id}/batches/retail     (Faz a saida de quantidades de um produto por varejo, ou seja, quantidades avulsas de forma FIFO)
  PUT -  v1/products/{id}/batches/wholesale  (Faz a saida de quantidades de um produto por atacado, ou seja, lotes fechados de forma FIFO) 

```

## 🕷️ Testes
Foram usadas as seguintes tecnologias e ferramentas para Testes da API:
* [JUnit 5](https://junit.org/junit5/docs/current/user-guide/) - Framework de Testes
* [Mockito](https://site.mockito.org/) - Estrutura de Testes

## 🚧🛠️ Tecnologias e Ferramentas

Foram usadas as seguintes tecnologias e ferramentas para a construção da API:
* [Java](https://www.java.com/pt-BR/) - Linguagem de Programação
* [SpringBoot](https://spring.io/) - FrameWork Java
* [PostgreSQL](https://www.postgresql.org/) - Sistema de gerenciamento de banco de dados relacional
* [Git](https://git-scm.com/) - Ferramenta de Versionamento de Código
* [IntelliJIDEA](https://www.jetbrains.com/pt-br/idea/) - IDE (Ambiente de desenvolvimento integrado)
* [Postman](https://www.postman.com/) - Plataforma da API
* [Swagger](https://swagger.io/tools/swagger-editor/) - Editar de design da API
* [Windows](https://www.microsoft.com/pt-br/windows/?r=1) - Sistema Operacional


[⬆ Voltar ao topo](#BatchControl)<br>


