


Bem Vindos a API do Desafio - DBCAMP
=================

![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/58f0e839-d5d8-4cba-8de0-a4c467749b06)

### Autor: João Vitor Nunes Carvalho

### Features
- ✅ Cadastrar Dado Meteorológico;
- ✅ Buscar todos os dados meteorológicos com paginação de 10 e ordem por data;
- ✅ Buscar por ID;
- ✅ Buscar por nome da Cidade;
- ✅ Buscar os próximos 7 dias da semana de uma Cidade;
- ✅ Editar dado meteorológico por ID;
- ✅ Deletar dado meteoroóligco por ID:
- ✅ Rota de HealthCheck;
<div    align="center">
<img height=200 src="https://cdn-icons-png.flaticon.com/512/3050/3050031.png"/>

### Principais Mentores:   
   <table style ="margin-top:10px;">
     <tr>
       <td align="center"><a href="https://github.com/riguelbf"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/7014252?v=4" width="100px;" alt=""/><br /><sub><b>Riguel Figueiredo</b></sub></a><br /><a>🛹🛹💙</a></td>
       <td align="center"><a href="https://github.com/sabrinassantos"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/8310243?v=4" width="100px;" alt=""/><br /><sub><b>Sabrina Santos</b></sub></a><br /><a>🐶🐕❤</a></td>
     </tr>
   </table>
</div>

   
Indice:
=======
<!--ts-->
* [Descrição do projeto](#descrição-do-projeto)
* [Pré-requisitos](#-pré-requisitos)
* [Tecnologias](#-tecnologias)
* [Iniciando o uso deste projeto](#-iniciando-o-uso-deste-projeto)
    * [Clone este repositório](#clone-este-repositório)
    * [Acesse a pasta do projeto no terminal/cmd](#acesse-a-pasta-do-projeto-no-terminalcmd)
    * [Instale as dependências](#instale-as-dependências)
    * [Gere o pacote executável](#gere-o-pacote-executável)
    * [Execute a aplicação](#execute-a-aplicação)
* [Arquitetura do projeto](#-arquitetura-do-projeto)
* [Banco de dados](#-banco-de-dados)
  * [Instalando o banco de dados PostgresSQL](#instalando-o-banco-de-dados-postgressql)
  * [Executando o banco de dados com docker](#executando-o-banco-de-dados-com-docker)
  * [Cliente para gerenciamento do banco de dados PostgresSQL](#cliente-para-gerenciamento-do-banco-de-dados-postgressql)
* [Migrations com Flyway](#migrations-com-flyway)
  * [Instalar Flyway](#instalar-flyway)
  * [Padrão de nome de arquivo de migração Flyway](#padrão-de-nome-de-arquivo-de-migração-flyway)
  * [Criando uma migration para o Flyway](#criando-uma-migration-para-o-flyway)
  * [Executando uma migration](#executando-uma-migration)
  * [Revertendo uma migration](#revertendo-uma-migration)
* [Sobre padrões no versionamento do código](#sobre-padrões-no-versionamento-do-código)
* [Acessando endpoints e utilizando a API](#acessando-endpoints-e-utilizando-a-api)
<!--te-->
<div    align="center">
<img height=200 src="https://cdn-icons-png.flaticon.com/512/1340/1340358.png"/>
</div>


## Descrição do Projeto
Projeto visando a apredizegem no backend, possuindo as seguintes configurações
- Arquitetura em 3 camadas;
- Docker para a aplicação;
- Docker para o banco de dados com PostgreSQL;
- Consultas com ORM configuradas;
- Open API docs com Swagger;
- Migrations com Flyway;
- Spring Boot;🍃
- JUnit e Mockito para os testes;🧪
- Actuator para HealthCheck;🏥

## 🧱 Pré-requisitos

Como pré requisitos temos os seguintes itens:
- Java 11 ou superior;
- Docker;
- Maven;
- Flyway;
- *OBS:Caso possua uma IDE como "IntelliJ", o Maven e Flyaway (e qualquer outra dependência inserida no POM) vão ser instalados automaticamente dentro do projeto quando clickar para dar reload no lifecycle do maven, como na imagem abaixo (símbolo com 2 setas girando):*

![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/b14d1602-042e-408c-924e-de020f49966d)

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [JDK 11 Releases](https://jdk.java.net/11/)
- [Flyway](https://flywaydb.org/)
- [Docker](https://docs.docker.com/)
- [Spring Boot](https://spring.io/)
- [Maven](https://maven.apache.org/what-is-maven.html)

## 🎬 Iniciando o uso deste projeto

### Clone este repositório
```bash
git clone git@github.com:dbserver/dbcamp-template-api.git
```

### Acesse a pasta do projeto no terminal/cmd
```bash
cd dbcamp-template-api
```

### Instale as dependências
```bash
mvn install:install-file -Dfile=./pom.xml -DpomFile=./pom.xml
```


### Gere o pacote executável
```bash
mvn clean package
```

### Execute a aplicação
```bash
java -jar target/template-0.0.1-SNAPSHOT.jar
```

### O servidor inciará na porta:4767 - acesse <http://localhost:4767>

## Acesso ao Open API docs (Swagger)

Para acessar o [Open APi (swagger)](https://swagger.io/specification/) da aplicação acesso o link abaixo;
```
http://localhost:4767/swagger-ui/index.html
```

Para acessar o APi docs (json file) da aplicação acesso o link abaixo;
```
http://localhost:4767/v3/api-docs
```


## 📁 Arquitetura do projeto

A arquitetura do projeto da-se com uma divisão em 3 camadas com responsabilidades distintas:

![img_1.png](docs/images/img_1.png)

### Camada de apresentação (Presentation)
Mantenha essa camada o mais fina possível e limitada à mecânica das operações MVC, por exemplo, recebendo e validando as entradas, manipulando o objeto modelo, retornando o objeto MovedAndView apropriado e assim por diante. Todas as operações relacionadas ao negócio devem ser feitas em classes de serviço. As classes do controlador geralmente são colocadas em um pacote do controlador.

### Camada de lógica de negócios (Business Logic)
Cálculos, transformações de dados, processos de dados e validações entre registros (regras de negócios) geralmente são feitos nessa camada. Eles são chamados pelas classes do controlador e podem chamar repositórios ou outros serviços. As classes de serviço geralmente são colocadas em um pacote de serviço.

### Camada de acesso a dados (Data)
A responsabilidade dessa camada é limitada às operações Criar, Recuperar, Atualizar e Excluir (CRUD) em uma fonte de dados, que geralmente é um banco de dados relacional ou não relacional. As classes de repositório geralmente são colocadas em um pacote de repositório.


## 🏬 Banco de dados

### Instalando o banco de dados PostgresSQL

- Abra uma nova janela de comando e execute o comando abaixo.

 
```bash
# Para sistemas unix
docker pull postgres
```

```bash
# Para sistemas windows
docker pull stellirin/postgres-windows
```

- Verificando se a o download da imagem foi realizado com sucesso.
```bash
docker images
```
- Verifique se a imagem do postgres aparece conforme abaixo

Para **sistemas unix** deverá aparecer algo parecido como o abaixo
```
postgres      latest    80c558ffdc31   7 days ago   379MB
```
Para **sistemas windows** deverá aparecer algo parecido como o abaixo
```
stellirin/postgres-windows      latest    80c558ffdc31   7 days ago   379MB
```

## Executando o banco de dados com docker

OBS: altere o <nome-da-imagem> de acordo com o seu sistema operacional
- Unix: postgres
- Windows: stellirin/postgres-windows

```bash
docker run --name postgresql -e POSTGRES_USER=templateuser -e POSTGRES_PASSWORD=templatepassword -p 5432:5432 -d <nome-da-imagem>
```
**No comando dado acima,**

- PostgreSQL é o nome do Docker Container.
 - -e POSTGRES_USER é o parâmetro que define um nome de usuário exclusivo para o banco de dados Postgres.
 - -e POSTGRES_PASSWORD é o parâmetro que permite definir a senha do banco de dados Postgres.
- -p 5432:5432 é o parâmetro que estabelece uma conexão entre a porta do host e a porta do contêiner do Docker. Nesse caso, ambas as portas são fornecidas como 5432, o que indica que as solicitações enviadas às portas do host serão redirecionadas automaticamente para a porta do contêiner do Docker. Além disso, 5432 também é a mesma porta onde o PostgreSQL estará aceitando requisições do cliente.
- -d  é o parâmetro que executa o Docker Container no modo desanexado, ou seja, em segundo plano. Se você acidentalmente fechar ou encerrar o Prompt de Comando, o Docker Container ainda será executado em segundo plano.
- <nome-da-imagem> é o nome da imagem do Docker que foi baixada anteriormente para executar o Docker Container.

**Para validar se o banco de dados foi executado com sucesso execute o comando abaixo**
- 
```bash
 docker ps --filter "name=postgresql"
```
**Deve ser exibido algo similar o abaixo**
```
CONTAINER ID   IMAGE      COMMAND                  CREATED         STATUS         PORTS                    NAMES
f33be708db53   postgres   "docker-entrypoint.s…"   4 minutes ago   Up 4 minutes   0.0.0.0:5432->5432/tcp   postgresql
```

## Cliente para gerenciamento do banco de dados PostgresSQL
Para gerenciar, executar scripts sugerimos a utilização do [DBeaver](https://dbeaver.io/download/)

## Migrations com Flyway

### Instalar Flyway

- Realize a instalação do flyway de acordo com o seu sistema operacional [nesse link aqui](https://flywaydb.org/download/community)
- Verifique se o comando flyway é reconhecido no terminal executando 
```bash
flyway -v
```
**Observações importantes:**
- O Flyway vem desabilitado para auto migrations por padrão, logo você terá que realizar as migrations manualmente
- As migrações pendentes em uma única transação em vez de uma transação por migrações pendentes. Portanto, se uma migração falhar, todas as migrações executadas antes serão revertidas.

### Padrão de nome de arquivo de migração Flyway
Já existe uma pasta criada onde colocar o arquivo de migração; está localizado em resources/db/migration. No entanto, o nome do arquivo deve seguir um padrão específico:

![img.png](docs/images/img.png)
- Parte 1: É a letra "v" em maiúscula. O nome sempre começa com esta letra.
- Parte 2: É a versão de migração; pode ser 1, 001, 1.2.3, 2021.09.24.12.55.32, ... você entendeu.
- Parte 3: São os dois sublinhados (_)
- Parte 4: A descrição da migração; você pode separar as palavras com um sublinhado ou um espaço.
- Parte 5: É a extensão do arquivo.sql

### Criando uma migration para o Flyway

Para criar arquivos de migrations siga os passos abaixo
- Crie um arquivo de migration com o padrão de nome descrito acima dentro do caminmho resources/db/migration

### Executando uma migration
- Execute o comando abaixo para executar as migrações
```bash
flyway migrate -configFiles=flyway.properties
```
   
**Observação: você deve estar no diretório do projeto para executar o comando.**

### Revertendo uma migration
Pode acontecer que você queira reverter uma migração; execute o comando abaixo:
```bash
flyway undo -configFiles=flyway.properties
```
## Sobre padrões no versionamento do código

É desejado que seja utilizado o padrão de Commits Semânticos. Pode entender melhor [nesse link](https://github.com/iuricode/padroes-de-commits)

## Acessando endpoints e utilizando a API
Por padrão nossa api está rodando na porta http://localhost:4767, o endpoint princpal é o "/api/meteorologicalInfo".
   - Criar um Dado Meteorológico: dentro da rota "http://localhost:4767/api/meteorologicalInfo" faça uma requisição do tipo POST seguindo o exemplo:
   
   ![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/0017ac7c-2a1d-42e8-9236-39cca6943332)
   
   - Buscar todos os dados: dentro da rota "http://localhost:4767/api/meteorologicalInfo" faça uma requisição do tipo GET, o retorno será uma paginação de 10 itens ordenados por data. 
   Para Navegar entre as paginas, adicione no endpoint "?page={pagina desejada}". Exemplo, quero acessar a pagina 2 = http://localhost:4767/api/meteorologicalInfo?page=1
   
   ![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/714e1d64-17b7-4907-9103-612cb9c2a2f7)

   *OBS: As paginas são como arrays, iniciam no indice 0, então se eu quiser acessar a página 1, tenho que ir para a ?page=0, pegina 2 = ?page=1 e assim sucessivamente*
   
   - Buscar dados por Cidade: basta adicionar o nome da cidade desejada na rota http://localhost:4767/api/meteorologicalInfo/find={cidade}, sendo "cidade" a variável que será adicionado o nome desejado.Faça uma requisição do tipo GET. Exemplo: http://localhost:4767/api/meteorologicalInfo/find=Salvador , o retorno será:
   
   ![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/6763cba1-a13f-48a1-8e5b-4ec3f0fb4ee5)
   
   - Buscar dados por ID: basta adicionar o id após a rota principal: http://localhost:4767/api/meteorologicalInfo/{id} , sendo "id" a variável que será o id de determinado dado. Faça uma requisição do tipo GET. Exemplo: http://localhost:4767/api/meteorologicalInfo/704 , o retorno será: 

   ![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/df6b5b70-9577-482e-8a70-ad0867b0f0e9)

   - Buscar os próximos 7 dias da semana de uma Cidade: bem semelhante ao de buscar por cidade, basta adicionar o nome da cidade desejada na rota http://localhost:4767/api/meteorologicalInfo/find={cidade} sendo "cidade" a variável que será adicionado o nome desejado. Faça uma requisição do tipo GET. Exemplo: http://localhost:4767/api/meteorologicalInfo/find=Salvador , o retorno será:
   
      ![image](https://github.com/DB-WeatherApp/dbcamp-template-api/assets/54593758/c29e302a-1d6f-40c4-9029-8cb348657d80)

   
-  Editar dado meteorológico por ID: faça uma requisição do tipo PUT na rota: http://localhost:4767/api/meteorologicalInfo . Nessa requisição o id deve ser passado dentro do body, além do ID, nenhuma outra informação meteorológica é obrigatória, sendo assim, você como usuário pode alterar tudo, ou apenas 1 item, fica a seu critério. Exemplo de alteração de nome de cidade e data: 
```bash
# Para requisição JSON
{
	"id": 603,
	"city":"Salvador" ,
	"weatherDate": "2023-04-20"
}
```
 
- Deletar dado meteoroóligco por ID: faça uma requisição do tipo DELETE, dentro da rota http://localhost:4767/api/meteorologicalInfo/{id}, sendo ID o identificador unico de determinado dado meteorológico. Exemplo: deletar o dado de ID 7000: http://localhost:4767/api/meteorologicalInfo/7000;
   
- Rota de HealthCheck:basta fazer uma requisição do tipo GET dentro da rota http://localhost:2000/health-check/health. 
   *OBS: a rota padrão foi alterada para 2000 para garantir a segurança da API, já que a health-check da acesso a dados importantes do sistema. Seria adicionado um sistema de segurança para acesso as rotas, porém os mentores recomendaram não se preocupar com isso agora*
   
   
   

   

   
   
