
# 🚀 API TRANSAÇÕES
Uma API REST desenvolvida em Java com Spring Boot.


## 📚 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Como Executar](#-como-executar)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Rotas da API](#-rotas-da-api)

## Sobre o Projeto
desafio de desenvolvimento de software Backend. Consiste em criar uma API REST que recebe Transações e retorna Estatísticas sob essas transações utilizando-se de Java ou Kotlin e Spring Boot.  
[Link do Desafio](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)  



## 🛠 Tecnologias
- Java 17
- Spring Boot
- Lombook
- Maven
- Lombook
- Swagger



## ⚙️ Funcionalidades
- [x] Configuração Banco de dados em memória  
- [x] POST /transacao - Adicionar transação  
- [x] DELETE /transacao - Deletar transaçães  
- [x] GET /estatistica -  estatísticas das transações que aconteceram nos últimos 60 segundos



- **EXTRAS**
- [ ] Testes automatizados
- [ ] Containerização 
- [x] Logs 
- [ ] Observabilidade 
- [ ] Performance 
- [x] Tratamento de Erros 
- [x] Documentação da API 
- [x] Documentação do Sistema  
- [x] Aplicação configurável 



## 🚀 Como Executar
```bash
# Clonar o repositório
git clone https://github.com/fcss-dev/itau-challenge-backend.git

# Entrar na pasta
cd itau-challenge-backend

# Executar com Maven
mvn spring-boot:run
```



## 📂 Estrutura do Projeto
```bash
src/  
    ├── service/  
    ├── controller/  
        ├── dtos/  
    ├── exceptions/  
    └── TransactionApiApplication.java 
```
 


## 🌐 Rotas da API
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| POST | /transacao | Recebe as Transações |
| DELETE | /transacao | Apaga todos os dados de transações |
| GET | /estatistica | retornar estatísticas das transações |

- Com a aplicação em execução acesssar:  
    - [Swagger](http://localhost:8080/swagger-ui.html)

  
