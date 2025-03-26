# Desafio API - Gerenciamento de Cursos

## Descrição
Este projeto tem como objetivo a implementação de uma API (Fictícia) para o gerenciamento de cursos, com funcionalidades de criação, atualização, listagem e exclusão de cursos. A API inclui uma tratativa robusta de exceções, 
proporcionando mensagens de erro claras e amigáveis para o usuário.

## Funcionalidades

- Criar Curso: Permite a criação de um novo curso com um nome e categoria.
- Atualizar Curso: Permite a atualização de um curso existente, baseado no seu ID.
- Listar Cursos: Exibe todos os cursos cadastrados na plataforma.
- Deletar Curso: Remove um curso da plataforma.

## Tecnologias Utilizadas
- Java: Linguagem utilizada para desenvolvimento da API.
- Spring Boot: Framework para criação da aplicação.
- JPA / Hibernate: Para o gerenciamento de persistência dos dados.
- UUID: Utilizado para a identificação única dos cursos.
- Exceções Customizadas: Para um tratamento adequado de erros e respostas amigáveis ao usuário.****

## Como Executar

#### 01 - Clone o repositório:
```
git clone https://github.com/seu-usuario/desafio-api.git
```

#### 02 - Navegue até o diretório do projeto:
```
cd desafio-api
```

#### 03 - Compile o projeto utilizando Maven:
```
mvn clean install
```

#### 03 - Execute a aplicação:
```
mvn spring-boot:run
```

#### A API estará disponível em http://localhost:8080.
