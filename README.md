# Gerenciador de Compromissos 


## Descrição
Este projeto tem como objetivo ajudar o usuário a gerenciar seus compromissos de forma automatizada e integrada com informações meteorológicas. O usuário poderá interagir com o sistema através do Telegram, enviando mensagens contendo informações sobre compromissos, como data, hora, descrição e localização (CEP, cidade e estado). O sistema irá armazenar, listar, modificar ou deletar esses compromissos, além de enviar alertas personalizados com a previsão do tempo para o local e horário definidos.

A aplicação permite:
- Adicionar compromissos com CEP, cidade e estado;

- Listar compromissos por dia, mês, cidade, estado ou CEP;

- Modificar compromissos existentes;

- Deletar compromissos;

- Configurar alerta antecipado, definindo o número de dias antes do compromisso para receber notificações;

- Enviar alertas via Telegram, incluindo informações do compromisso e previsão do tempo para a localidade e horário definidos.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2**
- **Notificações Telegram Bot API**
- **Banco de dados MySQL**
- **JPA (Java Persistence API)**
- **Gerenciamento de Migrations: Flyway**
- **Integração de Previsão do Tempo: API de clima (exemplo: OpenWeatherMap)**
- **Docker** (para containerização)

## Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── com/empresa/compromissos/
│   │       ├── config/              # Configuração de Telegram Bot, API de Clima, etc.
│   │       ├── controller/          # REST e/ou Telegram Controller
│   │       ├── dto/                 # Objetos de transferência de dados (input/output)
│   │       ├── exception/           # Tratamento de exceções personalizadas
│   │       ├── mapper/              # Conversores entre Entity <-> DTO
│   │       ├── model/               # Entidades JPA (dados persistidos)
│   │       ├── repository/          # Repositórios JPA
│   │       ├── scheduler/           # Tarefas agendadas (envio de alertas)
│   │       └── service/             # Regras de negócio
│   └── resources/
│       ├── application.properties   # Configurações da aplicação
│       └── db/
│           └── migration/           # Scripts Flyway
└── test/
    └── java/                        # Testes automatizados
```

## Endpoints de Telegram

A interação do usuário com a aplicação ocorre exclusivamente via Telegram, por meio do bot configurado. O bot recebe mensagens formatadas com informações dos compromissos e responde conforme as ações solicitadas.

Como funciona:

- O usuário envia comandos e mensagens formatadas para o bot do Telegram.

- O Controller interpreta essas mensagens e aciona os serviços correspondentes para cadastrar, listar, alterar ou deletar compromissos.

- O bot responde com mensagens de confirmação, listas de compromissos, informações atualizadas e alertas programados.

**Exemplos de comandos/mensagens suportadas:**
```markdown
| Comando / Mensagem                                   | Ação Executada                                  |
|------------------------------------------------------|-------------------------------------------------|
| /adicionar + detalhes do compromisso                 | Cadastra um novo compromisso                    |
| /listar + filtros (data, mês, cidade, estado, cep)   | Lista compromissos conforme filtros fornecidos  |
| /alterar + ID + novos dados                          | Atualiza um compromisso existente               |
| /deletar + ID                                        | Remove um compromisso do sistema                |
```

## Funcionalidades

### 1. Adicionar Compromisso
Esta funcionalidade permite ao usuário cadastrar um novo compromisso, fornecendo informações detalhadas como data, hora, descrição e localização (CEP, cidade e estado). Também é possível configurar a antecedência para receber alertas via Telegram.

**Como usar:**

O usuário deve enviar uma mensagem para o bot Telegram com os detalhes do compromisso no formato esperado. Alternativamente, via API REST, o compromisso pode ser criado através do endpoint `/compromissos` enviando um JSON com as informações.

**Campos necessários:**
```markdown
| Campo          | Tipo    | Descrição                                           |
|----------------|---------|-----------------------------------------------------|
| titulo         | String  | Título ou nome do compromisso                       |
| descricao      | String  | Descrição detalhada do compromisso (opcional)       |
| data           | String  | Data do compromisso (formato: YYYY-MM-DD)           |
| hora           | String  | Hora do compromisso (formato: HH:mm)                |
| cep            | String  | CEP do local do compromisso (formato: NNNNN-NNN)    |
| cidade         | String  | Cidade onde ocorrerá o compromisso                  |
| estado         | String  | Estado (UF) da cidade                               |
| alertaDiasAntes| Integer | Número de dias antes para receber o alerta          |
```

**Exemplo de mensagem via Telegram**
```markefile
/adicionar
Título: Consulta médica
Descrição: Consulta com cardiologista
Data: 2025-10-01
Hora: 14:00
CEP: 01310-000
Cidade: São Paulo
Estado: SP
AlertaDiasAntes: 3
```
**Exemplo de JSON para API REST**
```json
{
  "titulo": "Consulta médica",
  "descricao": "Consulta com cardiologista",
  "data": "2025-10-01",
  "hora": "14:00",
  "cep": "01310-000",
  "cidade": "São Paulo",
  "estado": "SP",
  "alertaDiasAntes": 3
}
```

**Resposta de sucesso:**
```json
{
  "id": 15,
  "titulo": "Consulta médica",
  "descricao": "Consulta com cardiologista",
  "data": "2025-10-01",
  "hora": "14:00",
  "cep": "01310-000",
  "cidade": "São Paulo",
  "estado": "SP",
  "alertaDiasAntes": 3,
  "mensagem": "Compromisso cadastrado com sucesso!"
}
```

### 2. Listar Compromissos
Permite ao usuário consultar os compromissos cadastrados, filtrando por dia, mês, cidade, estado ou CEP.

**Parâmetros de filtro disponíveis:** 
```markdown 
| Parâmetro | Tipo   | Descrição                                            |
|-----------|--------|------------------------------------------------------|
| data      | String | Data para filtrar compromissos (formato: YYYY-MM-DD) |
| mes       | String | Mês para filtrar compromissos (formato: YYYY-MM)     |
| cidade    | String | Nome da cidade para filtrar                          |
| estado    | String | UF (estado) para filtrar                             |
| cep       | String | CEP para filtrar compromissos                        |
```

**Resposta de sucesso:**

```json
[
  {
    "id": 15,
    "titulo": "Consulta médica",
    "descricao": "Consulta com cardiologista",
    "data": "2025-10-01",
    "hora": "14:00",
    "cep": "01310-000",
    "cidade": "São Paulo",
    "estado": "SP",
    "alertaDiasAntes": 3
  },
  {
    "id": 18,
    "titulo": "Reunião com equipe",
    "descricao": "Discussão do projeto",
    "data": "2025-10-01",
    "hora": "16:00",
    "cep": "01310-000",
    "cidade": "São Paulo",
    "estado": "SP",
    "alertaDiasAntes": 1
  }
]
```

### 3. Modificar Compromisso
Permite ao usuário alterar os dados de um compromisso existente, identificado pelo seu ID.

**Campos permitidos para atualização:**
```markdown 
| Campo          | Tipo    | Descrição                                            |
|----------------|---------|------------------------------------------------------|
| titulo         | String  | Novo título ou nome do compromisso                   |
| descricao      | String  | Nova descrição detalhada do compromisso              |
| data           | String  | Nova data do compromisso (formato: YYYY-MM-DD)       |
| hora           | String  | Nova hora do compromisso (formato: HH:mm)            |
| cep            | String  | Novo CEP do local do compromisso                     |
| cidade         | String  | Nova cidade onde ocorrerá o compromisso              |
| estado         | String  | Novo estado (UF) da cidade                           |
| alertaDiasAntes| Integer | Novo número de dias antes para receber o alerta      |
```
A requisição será enviada via HTTP PUT para o endpoint, por exemplo:
```bash
PUT /compromissos/{id}
```

**Exemplo de entrada:**

```json
{
  "titulo": "Consulta cardiológica atualizada",
  "descricao": "Consulta revisada com o cardiologista",
  "data": "2025-10-02",
  "hora": "15:30",
  "cep": "01310-001",
  "cidade": "São Paulo",
  "estado": "SP",
  "alertaDiasAntes": 2
}
```

**Resposta de sucesso:**
```json
{
  "id": 15,
  "titulo": "Consulta cardiológica atualizada",
  "descricao": "Consulta com cardiologista",
  "data": "2025-10-01",
  "hora": "15:00",
  "cep": "01310-000",
  "cidade": "São Paulo",
  "estado": "SP",
  "alertaDiasAntes": 2,
  "mensagem": "Compromisso atualizado com sucesso!"
}
```

### 4. Deletar Compromisso

Permite ao usuário excluir um compromisso pelo seu ID.

**Campo necessário:**
```markdown 
| Campo          | Tipo    | Descrição                    |
|----------------|---------|------------------------------|
| id             | Long    | Identificador do compromisso |
```

A requisição será enviada via HTTP DELETE para o endpoint, por exemplo:
```bash
DELETE /compromissos{id}
```

**Resposta de sucesso:**
```json
{
  "id": 15,
  "mensagem": "Compromisso removido com sucesso!"
}
```

## Modelagem de Dados
```pgsql
+---------------------------------------------------+
|                   Compromisso                     |
+----------------+----------------------------------+
| id             | BIGINT (PK)                      |
| titulo         | VARCHAR(255)                     |
| descricao      | TEXT                             |
| data           | DATE                             |
| hora           | TIME                             |
| cep            | VARCHAR(9)                       |
| cidade         | VARCHAR(100)                     |
| estado         | VARCHAR(2)                       |
| alertaDiasAntes| INT                              |
| criadoEm       | TIMESTAMP                        |
| atualizadoEm   | TIMESTAMP                        |
+---------------------------------------------------+
```

**Considerações:**

- A tabela Compromisso armazena todas as informações necessárias para o gerenciamento dos compromissos.

- A aplicação pode utilizar índices nos campos data, cidade, estado e cep para otimizar consultas.

- O campo alertaDiasAntes será usado para calcular quando o alerta deve ser disparado via Telegram.

- A data e hora de criação e atualização ajudam no controle de auditoria e sincronização.

### Entidade `CompromissoEntity`
Esta entidade representa um compromisso que o usuário irá gerenciar (adicionar, listar, modificar, deletar).

**Entidade CompromissoEntity:**
```java
    @Entity
    @Table(name = "tb_compromisso")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CompromissoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String titulo;

        @Column(columnDefinition = "TEXT")
        private String descricao;

        @Column(nullable = false)
        private LocalDate data;

        @Column(nullable = false)
        private LocalTime hora;

        @Column(length = 9, nullable = false)
        private String cep;

        @Column(length = 100, nullable = false)
        private String cidade;

        @Column(length = 2, nullable = false)
        private String estado;

        @Column(name = "alerta_dias_antes", nullable = false)
        private Integer alertaDiasAntes;

        @Column(name = "criado_em", updatable = false)
        private LocalDateTime criadoEm;

        @Column(name = "atualizado_em")
        private LocalDateTime atualizadoEm;

        @PrePersist
        public void prePersist() {
            this.criadoEm = LocalDateTime.now();
            this.atualizadoEm = LocalDateTime.now();
        }

        @PreUpdate
        public void preUpdate() {
            this.atualizadoEm = LocalDateTime.now();
        }
    }
```
### Atributos do Compromisso

```markdown
| Atributo          | Tipo           | Descrição                                                                |
|-------------------|----------------|--------------------------------------------------------------------------|
| `id`              | Long           | Identificador único do compromisso. Gerado automaticamente pelo sistema. |
| `titulo`          | String         | Título curto ou nome do compromisso.                                     |
| `descricao`       | String (Texto) | Descrição detalhada do compromisso (opcional).                           |
| `data`            | LocalDate      | Data em que o compromisso irá ocorrer (formato: `YYYY-MM-DD`).           |
| `hora`            | LocalTime      | Horário do compromisso (formato: `HH:mm`).                               |
| `cep`             | String         | Código de Endereçamento Postal (CEP) do local do compromisso.            |
| `cidade`          | String         | Cidade onde ocorrerá o compromisso.                                      |
| `estado`          | String         | Estado (UF) correspondente à cidade do compromisso.                      |
| `alertaDiasAntes` | Integer        | Número de dias de antecedência para envio de alerta.                     |
| `criadoEm`        | LocalDateTime  | Data e hora de criação do compromisso. Gerado automaticamente.           |
| `atualizadoEm`    | LocalDateTime  | Data e hora da última atualização. Atualizado automaticamente.           |
```

**Script SQL para criação da tabela:**

````SQL
CREATE TABLE tb_compromisso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    cep VARCHAR(9) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    alerta_dias_antes INT NOT NULL CHECK (alerta_dias_antes >= 0),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
````

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
# Etapa 1: Construção (build) da aplicação
        FROM maven:3.9.6-eclipse-temurin-17 as builder

        WORKDIR /app

        # Copia todos os arquivos para o container e realiza o build
        COPY . .
        RUN mvn clean package -DskipTests

        # Etapa 2: Imagem final com apenas o JAR
        FROM eclipse-temurin:17-jre

        WORKDIR /app

        # Copia apenas o JAR gerado da etapa anterior
        COPY --from=builder /app/target/gerenciadorcompromissos-0.0.1-SNAPSHOT.jar app.jar

        # Expõe a porta da aplicação
        EXPOSE 8080

        # Comando de inicialização da aplicação
        ENTRYPOINT ["java", "-jar", "app.jar"]
```

Explicação do Dockerfile:

* FROM openjdk:17-jre-slim: Usa uma imagem base leve com Java 17 JRE para rodar a aplicação.

* WORKDIR /app: Define o diretório padrão para comandos dentro do container.

* COPY target/gerenciador-compromissos.jar /app/gerenciador-compromissos.jar: Copia o arquivo JAR gerado pela build Maven para o container.

* EXPOSE 8080: Informa ao Docker que o container irá escutar a porta 8080.

* ENTRYPOINT ["java", "-jar", "gerenciador-compromissos.jar"]: Define o comando padrão para iniciar a aplicação Java.

### 2. 📦 Documentação do Serviço MySQL no `docker-compose.yml`

Define dois serviços: app e mysql.

* app: constrói a imagem Docker usando o Dockerfile na raiz (context: .).

* Mapeia a porta 8080 da aplicação para o host.

* Passa variáveis de ambiente para conectar ao banco MySQL pelo hostname mysql (nome do serviço).

* mysql: utiliza a imagem oficial do MySQL 8.0, configurada via variáveis de ambiente.

* Monta volume para configuração personalizada do MySQL (pode ser vazio ou conter .cnf).

* Ambos os serviços estão na mesma rede app-network para comunicação interna.

```java
version: '3.8'

        services:
        app:
        build:
        context: ..
        dockerfile: Dockerfile
        ports:
        - "8080:8080"  # Só a app é exposta para o host
        depends_on:
        - mysql
        environment:
        - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/db_example
        - SPRING_DATASOURCE_USERNAME=springuser
        - SPRING_DATASOURCE_PASSWORD=ThePassword

        mysql:
        image: mysql
        expose:
        - "3306"  # Visível apenas para os containers da mesma rede
        environment:
        - MYSQL_USER=springuser
        - MYSQL_PASSWORD=ThePassword
        - MYSQL_DATABASE=db_example
        - MYSQL_ROOT_PASSWORD=root
        volumes:
        - "./conf.d:/etc/mysql/conf.d:ro"
```

### 🔒 Segurança de Exposição
A porta do banco de dados não é exposta para o host, garantindo que o MySQL só possa ser acessado pela aplicação dentro da mesma rede Docker.


###Descrição Geral
Este serviço configura uma instância do MySQL utilizando a imagem oficial do Docker Hub. Ele define variáveis de ambiente, mapeamento de portas, e monta um volume com arquivos de configuração personalizados.

### ⚙️ Parâmetros do Serviço
`image: mysql`
Utiliza a imagem oficial do MySQL. A versão padrão será a mais recente, a menos que especificado (ex: `mysql:8.0`).


🌐 `ports`

| Porta no Host | Porta no Container | Descrição             |
| ------------- | ------------------ | --------------------- |
| 3306          | 3306               | Porta padrão do MySQL |


🌱 `environment`

| Variável              | Descrição                                             |
| --------------------- | ----------------------------------------------------- |
| `MYSQL_USER`          | Nome do usuário padrão que será criado (`springuser`) |
| `MYSQL_PASSWORD`      | Senha para o usuário padrão (`ThePassword`)           |
| `MYSQL_DATABASE`      | Nome do banco de dados a ser criado (`db_example`)    |
| `MYSQL_ROOT_PASSWORD` | Senha do usuário root do MySQL (`root`)               |

Monta um volume com arquivos de configuração personalizados do MySQL:

📁 `volumes`
``` yaml
- "./conf.d:/etc/mysql/conf.d:ro"
```
- `./conf.d` é o diretório local onde você pode adicionar arquivos `.cnf` para configurar o MySQL.

- Os arquivos são montados como somente leitura (`ro`).

- Exemplo de uso: ajustar parâmetros como tamanho do buffer, charset padrão, etc.

### ✅ Requisitos
- Docker e Docker Compose instalados.

- Docker Compose instalado

- Projeto compilado (mvn clean install) gerando o JAR em target/

### 3. ▶️ Rodando o Docker
Para rodar a aplicação e o MySQL usando Docker Compose, execute os seguintes comandos:

1- Construa as imagens:
```java
docker-compose up build
```

Este comando:

* Constrói a imagem da aplicação usando o Dockerfile.

* Inicia o banco de dados MySQL.

* Inicia sua aplicação Spring Boot.

* A aplicação estará disponível na porta http://localhost:8080.

## Como Rodar o Projeto com Docker

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
#### 3- Compile o projeto:

```java
mvn clean install
```

#### 4 - Construa as imagens e suba os containers:

Na pasta docker, onde está localizado o arquivo docker-compose.yml), execute:
```java
docker-compose up --build
```

Esse comando vai:
-Construir a imagem Docker da aplicação Spring Boot.

-Baixar e iniciar o container do MySQL.

-Subir ambos os containers na mesma rede Docker.

#### 5 - Acesse a aplicação:

Após os containers estarem rodando, sua aplicação estará disponível em:

```aidl
http://localhost:8080
``` 
#### 6- Testando a aplicação
Você pode acessar os endpoints da API, por exemplo via navegador ou ferramentas como Postman, usando a URL acima.

Acesse a documentação interativa em:
```aidl
http://localhost:8080/swagger-ui.html
``` 
