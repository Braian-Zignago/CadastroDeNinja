# Sistema de Cadastro de Ninjas

Bem-vindo ao Sistema de Cadastro de Ninjas!
Este projeto é uma aplicação de arquitetura em camadas desenvolvida com Spring Boot, projetada para cadastrar ninjas e suas respectivas missões. O projeto utiliza H2 como banco de dados em memória, Flyway para migrações de banco de dados e segue boas práticas de desenvolvimento de software como controle de versão com Git e hospedagem de repositório no GitHub.

Este sistema foi desenvolvido para gerenciar ninjas e suas missões. Cada ninja pode ser atribuído a uma única missão, enquanto uma missão pode ser associada a vários ninjas. As principais funcionalidades incluem:

---
## 📦 Estrutura do Projeto
```
/
├─ src/
│  ├─ Missoes/
│  │  ├─ MissoesController
│  │  ├─ MissoesDTO
│  │  ├─ MissoesMapper
│  │  ├─ MissoesModel
│  │  ├─ MissoesRepository
│  │  ├─ MissoesService
│  ├─ Ninjas/
│  │  ├─ NinjaController
│  │  ├─ NinjaDTO
│  │  ├─ NinjaMapper
│  │  ├─ NinjaModel
│  │  ├─ NinjaRepository
│  │  ├─ NinjaService
│  │
│  ├─ CadastroDeNinjasApplication.java
```

---
Ninja: Contém atributos como id, nome, idade, email, missões e rank. <br>
Missão: Contém atributos como id, título e descrição. <br>
Um Ninja pode ter apenas uma Missão, mas uma Missão pode ser atribuída a vários Ninjas.

## Design do Banco de Dados
![Esquema do banco de dados](images/diagram.png)

---
## Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2%20Database-40616D?style=for-the-badge&logo=h2&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0F43?style=for-the-badge&logo=flyway&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-E67824?style=for-the-badge&logo=hibernate&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![SQL](https://img.shields.io/badge/SQL-303E48?style=for-the-badge&logo=postgresql&logoColor=white)
---

## Configuração
1. Clone o repositório:
   ```bash
   git@github.com:Braian-Zignago/CadastroDeNinja.git
    ```
2. Navegue até o diretório do projeto:
   ```bash
   cd cadastro-de-ninjas
   ```
3. Configure as variaveis de ambiente no arquivo `application.properties` e também no IntelliJ. <br><br>

4. Construa o projeto:
   ```bash
    mvn clean install
    ```
5. Execute a aplicação:
    ```bash
     mvn spring-boot:run
     ```
6. Acesse a aplicação em http://localhost:8080
---
## Comandos possivelmente utilizaveis
Para resetar o id do banco de dados, use o seguinte comando manualmente:
```sql
ALTER TABLE TB_CADASTRO ALTER COLUMN id RESTART WITH "id+1";
```
"id+1" quer dizer o ultimo id da sua tabela mais um, exemplo: se seu Ultimo ID for 4, você vai somar mais 1 e colocar no comando SQL 5.