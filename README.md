# Gerenciador de Compromissos 


## Descrição

Este projeto tem como objetivo ajudar o usuário a gerenciar seus compromissos de forma automatizada. O usuário poderá interagir com o sistema através do **Telegram**, enviando mensagens com informações sobre compromissos (data, hora, descrição) e o sistema irá armazenar, listar, modificar ou deletar esses compromissos.

A aplicação permite:
- Adicionar compromissos.
- Listar compromissos por dia ou mês.
- Modificar compromissos existentes.
- Deletar compromissos.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2**
- **Telegram Bot API**
- **Banco de dados MySQL**
- **JPA (Java Persistence API)**
- **Docker** (para containerização)

## Estrutura do Projeto

- **Controller**: Gerencia as interações com o usuário via Telegram.
- **Service**: Contém a lógica de negócios para adicionar, listar, modificar e deletar compromissos.
- **Repository**: Responsável pela persistência dos dados no banco de dados.
- **Model**: Contém a estrutura de dados dos compromissos.

## Endpoints de Telegram

O usuário interage com o bot enviando mensagens formatadas conforme demonstrado no tópico de funcionalidades.


## Funcionalidades

### 1. Adicionar Compromisso


**Exemplo de entrada:**

```javascript
Assunto: Adicionar compromisso
Data: 30/06/2025
Hora: 16:00
Descrição: Aniversário Raphaela
Local: Salão de festas Além da Alegria
```

**Resposta de sucesso:**

```javascript
Compromisso adicionado com sucesso! 
ID: 12345 
Data: 30/06/2025 
Hora: 16:00
Descrição: Aniversário Raphaela
Local: Salão de festas Além da Alegria
```


### 2. Listar Compromissos

**Exemplo de entrada:**

```javascript
Assunto: Listar compromissos 
Data: 30/06/2025
```

**Resposta de sucesso:**

```javascript
Compromissos no dia 30/06/2025: 
ID: 12345 
Hora: 16:00
Descrição: Aniversário Raphaela
Local: Salão de festas Além da Alegria

ID: 67892 
Hora: 10:00
Descrição: Manutenção unhas de gel
Local: Salão CasemiroNails
```


### 3. Modificar Compromisso

**Exemplo de entrada:**

```javascript
Assunto: Modificar compromisso 
ID: 67892  
Hora: 08:00
```

**Resposta de sucesso:**

```javascript
Compromisso modificado com sucesso! 
ID: 67892 
Hora: 08:00
Descrição: Manutenção unhas de gel
Local: Salão CasemiroNails
```


### 4. Deletar Compromisso

**Exemplo de entrada:**

```javascript
Assunto: Deletar compromisso 
ID: 67892  
```
**Resposta de sucesso:**

```javascript
Compromisso deletado com sucesso! 
ID: 67892
```

## Modelagem de Dados

### Entidade `Compromisso`
Esta entidade representa um compromisso que o usuário irá gerenciar (adicionar, listar, modificar, deletar).

```java
@Entity
public class Compromisso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Strig data;
    
    @Column(nullable = false)
    private LocalTime hora;
    
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String local;
    
    //@Column(nullable = false, unique = true)
    //private String idTelegram;
    
    // Getters e Setters
}

```
### Atributos do Compromisso

- id: Identificador único do compromisso no banco de dados.

- data: A data em que o compromisso ocorrerá.

- hora: O horário do compromisso.

- descricao: Descrição do compromisso (ex.: "Aniversário Raphaela").

- local: Descrição do local do compromisso (ex.: Salão de festas Além da Alegria)

- idTelegram: Identificador único gerado para o compromisso para integração com o Telegram.

```sql
CREATE TABLE compromisso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    local VARCHAR(255) NOT NULL,
    idTelegram VARCHAR(255) NOT NULL UNIQUE
);
```

## Configuração do Banco de Dados MySQL
Para conectar ao banco de dados MySQL, ajuste as configurações no arquivo application.properties:

```java
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=usuario
spring.datasource.password=senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
- Substitua nome_do_banco, usuario e senha pelas credenciais do seu banco MySQL.

## Configuração do Docker

A seguir, estão as instruções para rodar o projeto e o banco de dados MySQL utilizando Docker.

### 1. Criando o `Dockerfile` para o Spring Boot
Crie um arquivo chamado `Dockerfile` na raiz do seu projeto com o seguinte conteúdo:

```java
# Usando a imagem do OpenJDK 17 como base
FROM openjdk:17-jre-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR gerado para dentro da imagem
COPY target/gerenciador-compromissos.jar /app/gerenciador-compromissos.jar

# Exponha a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "gerenciador-compromissos.jar"]
```

### 2. Criando o `docker-compose.yml`

Para rodar a aplicação e o banco de dados MySQL de forma simples, você pode usar o Docker Compose. Crie um arquivo chamado `docker-compose.yml` na raiz do seu projeto com o seguinte conteúdo:

```java
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/gerenciador_compromissos
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
      - SPRING_JPA_HIBERNATE_DDL-AUTO=update
    depends_on:
      - db
    networks:
      - app-network

  db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: gerenciador_compromissos
    ports:
      - "3306:3306"
    networks:
      - app-network

networks:
  app-network:
    driver: bridge


```

Esse arquivo docker-compose.yml define dois containers:

- app: O container que executará sua aplicação Spring Boot.

- db: O container que executará o banco de dados MySQL.

### 3. Rodando o Docker
Para rodar a aplicação e o MySQL usando Docker Compose, execute os seguintes comandos:

1- Construa as imagens:
```java
docker-compose build
```

2- Inicie os containers:
```java
docker-compose up
```

Isso irá subir tanto a aplicação Spring Boot quanto o MySQL. O banco de dados MySQL estará acessível no container `db` e a aplicação Spring Boot estará disponível na porta `8080` da sua máquina local.

## Como Rodar o Projeto
#### Pré-requisitos
- Docker e Docker Compose instalados

- Java 17 instalado

- Maven instalado

#### Passos para executar:

#### 1- Clone o repositório:

```java
git clone <url_do_repositorio>
cd <diretorio_do_projeto>
```

#### 2- Configure o bot do Telegram:

- Crie um bot no Telegram usando o BotFather.

- Obtenha o token do bot.

Configure as variáveis de ambiente no `application.properties`:

```java
telegram.bot.token=SEU_TOKEN_AQUI
telegram.bot.username=SEU_NOME_DE_USUARIO_AQUI
```
#### 4- Compile o projeto:

```java
mvn clean install
```

#### 5 - Execute o Docker Compose:

```java
docker-compose up
```