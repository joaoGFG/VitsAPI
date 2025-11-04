# Verdantis (VITS - Visual Information Tracking System)

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?style=flat-square&logo=springboot)
![Oracle](https://img.shields.io/badge/Oracle-Database-red?style=flat-square&logo=oracle)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=flat-square)

## 📋 Sobre o Projeto

O **Verdantis** é um sistema de rastreabilidade e visualização inteligente desenvolvido para transformar o agronegócio brasileiro. O Brasil perde mais de **R$ 12 bilhões anuais** em exportações por falta de rastreabilidade e certificação sustentável. Nossa solução une **Oracle Database**, **Spring Boot** e tecnologias modernas para oferecer transparência, sustentabilidade e valor agregado aos produtos agrícolas.

### 🎯 Problema que Resolve

- **Falta de rastreabilidade**: Dificulta exportações e certificações sustentáveis
- **Gestão ineficiente**: Desperdício de água, energia e insumos
- **Baixa digitalização**: Pequenos produtores sem acesso a ferramentas modernas
- **Pressão internacional**: Mercados EUA/UE rejeitam produtos sem comprovação de origem

### 💡 Solução

Uma plataforma integrada de rastreabilidade digital que conecta produtores, distribuidores e compradores, permitindo registro, visualização e certificação de cadeias produtivas agrícolas.

---

### PDF da documentação completa em /documentation

---

## ⚠️ Tipos de Usuário

**Para criar um usuário, você precisa referenciar um tipo existente.** Consulte os tipos disponíveis via:

```http
GET http://localhost:8080/user-types
```

### Por que classe ao invés de enum?

Optamos por **entidade JPA** para permitir:
- Adicionar novos tipos dinamicamente
- Gerenciamento via banco de dados Oracle
- Normalização e integridade referencial
- Conselho do professor de Banco de Dados

<img width="458" height="216" alt="image" src="https://github.com/user-attachments/assets/627515bf-f036-4a91-9ce8-0650143737f6" />


## 👥 Equipe GreenCore

### Integrantes e Responsabilidades

| Nome | Responsabilidades |
|------|-------------------|
| **Caio Lucas Silva Gomes** | Frontend (Next.js/React Native), Documentação, Design, Banco de Dados Oracle |
| **João Gabriel Fuchs Grecco** | Backend (Spring Boot), API .NET, Oracle APEX, Banco de Dados Oracle |
| **Madjer Henrique Almeida Finamor** | IoT, Cloud & DevOPS, QA |

---

## 🚀 Como Rodar a Aplicação

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8+**
- **Oracle Database** (configurado e acessível)
- **Git**

### Configuração do Banco de Dados

1. Configure as credenciais do banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

### Passos para Execução

#### Opção 1: Usando Maven Wrapper (Recomendado)

```bash
# Clone o repositório
git clone https://github.com/joaoGFG/VitsAPI.git

# Entre no diretório do projeto
cd VitsAPI/vits-agrochain

# Execute a aplicação (Windows)
mvnw.cmd spring-boot:run

# Execute a aplicação (Linux/Mac)
./mvnw spring-boot:run
```

#### Opção 2: Usando Maven Instalado

```bash
# Clone o repositório
git clone https://github.com/joaoGFG/VitsAPI.git

# Entre no diretório do projeto
cd VitsAPI/vits-agrochain

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

### Verificação da API

Acesse o endpoint de status:
```
GET http://localhost:8080/
```

Resposta esperada: `"Verdantis API is running"`

---

## Credenciais do Banco de Dados para funcionamento presentes no PDF de documentação.

## 🏗️ Arquitetura e Diagramas

### Diagrama de Entidade-Relacionamento (DER) (De zoom para melhor visibilidade)

![DER]<img width="1969" height="816" alt="image" src="https://github.com/user-attachments/assets/366dd006-047f-4339-8cb8-7e0072e6b147" />

### Diagrama de Classes UML

![Diagrama de Classes UML]<img width="1163" height="1079" alt="image" src="https://github.com/user-attachments/assets/bd8b0f6f-5241-48b3-a29e-e91d049d372d" />

### Arquitetura da Solução

```
┌─────────────────────────────────────────────────────┐
│                   FRONTEND LAYER                    │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────┐  │
│  │  Next.js +   │  │ React Native │  │   APEX    │  │
│  │    React     │  │    Mobile    │  │ Dashboards│  │
│  └──────────────┘  └──────────────┘  └───────────┘  │
└─────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────┐
│                   BACKEND (API)                     │
│  ┌──────────────────────────────────────────────┐   │
│  │              Spring Boot (Java 17)           │   │
│  │  • UserController (REST API)                 │   │
│  │  • Repository Pattern (JPA)                  │   │
│  │  • Validation & Exception Handling           │   │
│  └──────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────┐
│             DATA PERSISTENCE LAYER                  │
│  ┌──────────────────────────────────────────────┐   │
│  │              Oracle Database                 │   │
│  │  • VITS_ORC_USUARIO                          │   │
│  │  • VITS_ORC_TIPO_USUARIO                     │   │
│  │  • VITS_ORC_PAIS / ESTADO / MUNICIPIO        │   │
│  │  • Sequences & Constraints                   │   │
│  └──────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────┐
│              BLOCKCHAIN LAYER (Futuro)              │
│  ┌──────────────────────────────────────────────┐   │
│  │             Hyperledger Fabric               │   │
│  │  • Registro Imutável de Lotes                │   │
│  │  • Hash de Segurança                         │   │
│  └──────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────┘
```

---

## 📹 Vídeo de Apresentação

🎬 **[Assista ao vídeo do Pitch](https://youtu.be/FNkgZbSAnHA)**

O vídeo apresenta:
- Proposta Tecnológica do Verdantis
- Público-alvo da aplicação (produtores, distribuidores, compradores)
- Problemas que a aplicação se propõe a solucionar
- Demonstração das funcionalidades principais

---

## 📡 Documentação da API (Endpoints)

### 🔗 Swagger UI

A documentação interativa da API está disponível através do Swagger UI:

**URL do Swagger:** http://localhost:8080/swagger-ui/index.html

Ou acesse diretamente:

**OpenAPI JSON:** http://localhost:8080/v3/api-docs

### Base URL
```
http://localhost:8080
```

### Endpoints Disponíveis

#### 1. Health Check
Verifica se a aplicação está em execução.

**Request:**
```http
GET /
```

**Response:**
```json
Status: 200 OK
Body: "Verdantis API is running"
```

---

#### 2. Listar Tipos de Usuário
Retorna todos os tipos de usuário disponíveis no sistema.

**Request:**
```http
GET /user-types
```

**Response:**
```json
Status: 200 OK
Content-Type: application/json

[
  {
    "userTypeId": 1,
    "userDescription": "Produtor Rural"
  },
  {
    "userTypeId": 2,
    "userDescription": "Gestor"
  },
  {
    "userTypeId": 3,
    "userDescription": "Comprador"
  }
]
```

---

#### 3. Buscar Tipo de Usuário por ID
Retorna um tipo de usuário específico.

**Request:**
```http
GET /user-types/{id}
```

**Response:**
```json
Status: 200 OK
Content-Type: application/json

{
  "userTypeId": 1,
  "userDescription": "Produtor Rural"
}
```

**Erro (404):**
```json
{
  "timestamp": "2025-10-11T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "UserType não encontrado com o id: 999"
}
```

---

#### 4. Listar Usuários
Retorna todos os usuários cadastrados.

**Request:**
```http
GET /users
```

**Response:**
```json
Status: 200 OK
Content-Type: application/json

[
  {
    "userId": 1,
    "userName": "João Silva",
    "registrationDate": "2025-10-11T14:30:00",
    "userType": {
      "userTypeId": 1,
      "userDescription": "Produtor Rural"
    }
  }
]
```

---

#### 5. Buscar Usuário por ID
Retorna um usuário específico.

**Request:**
```http
GET /users/{id}
```

**Response:**
```json
Status: 200 OK
Content-Type: application/json

{
  "userId": 1,
  "userName": "João Silva",
  "registrationDate": "2025-10-11T14:30:00",
  "userType": {
    "userTypeId": 1,
    "userDescription": "Produtor Rural"
  }
}
```

**Erro (404):**
```json
{
  "timestamp": "2025-10-11T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuário não encontrado com o id: 999"
}
```

---

#### 6. Criar Usuário
Cria um novo usuário no sistema (produtor, gestor ou comprador).

**Request:**
```http
POST /users
Content-Type: application/json

{
  "userName": "João Silva",
  "registrationDate": "2025-10-11T14:30:00",
  "userType": {
    "userTypeId": 1
  }
}
```

**Response:**
```json
Status: 201 CREATED
Content-Type: application/json

{
  "userId": 1,
  "userName": "João Silva",
  "registrationDate": "2025-10-11T14:30:00",
  "userType": {
    "userTypeId": 1,
    "userDescription": "Produtor Rural"
  }
}
```

**Validações:**
- `userName`: Obrigatório, máximo 200 caracteres
- `registrationDate`: Obrigatório (formato ISO-8601: YYYY-MM-DDTHH:mm:ss)
- `userType.userTypeId`: Obrigatório, deve existir no banco de dados

---

## 🧪 Testes da Aplicação

### Estratégia de Testes

O projeto foi testado utilizando as seguintes abordagens:

**Testes de Integração**: Verificação da integração com Oracle Database
**Testes de API**: Documentação e testes realizados via Postman

### Executando os Testes

![Get Users]<img width="1258" height="776" alt="image" src="https://github.com/user-attachments/assets/b80b4585-9646-4808-9ea2-bc25b07fbea8" />


![GetByID Users]<img width="1263" height="695" alt="image" src="https://github.com/user-attachments/assets/392c332a-461f-4015-9955-9226708701a1" />


![Post Users]<img width="1278" height="754" alt="image" src="https://github.com/user-attachments/assets/b17a45d8-5741-41d6-8951-9f02af2324e8" />


![Get User Types]<img width="1267" height="723" alt="image" src="https://github.com/user-attachments/assets/7ec65c96-5347-405f-98b3-6345c9330c79" />


![GetByID User Types]<img width="1267" height="658" alt="image" src="https://github.com/user-attachments/assets/dfea3448-7c17-4b3e-bf3b-5ccd347a4636" />


### Coleção de Testes (Postman)

**[Coleção Postman](https://cloudy-shadow-8140611.postman.co/workspace/My-Workspace~181a7838-1c82-4701-819f-ec9b6ab67aea/collection/45051985-d29b9f5f-5832-40d5-9c30-8768ab5d3b53?action=share&creator=45051985)**

#### Endpoints (Swagger)

![Endpoints]<img width="1651" height="604" alt="image" src="https://github.com/user-attachments/assets/a2d06088-e75e-40b0-bf3a-c705568bfc75" />

---

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia | Função |
|-----------|------------|--------|
| **Backend** | Java | Linguagem de programação |
| **Framework** | Spring Boot | Framework para API REST |
| **Persistência** | Spring Data JPA | Camada de acesso a dados |
| **Banco de Dados** | Oracle Database | Armazenamento principal |
| **Driver JDBC** | ojdbc11 | Conexão com Oracle |
| **Validação** | Hibernate Validator | Validação de dados |
| **Build** | Maven | Gerenciamento de dependências |
| **Utilitários** | Lombok | Redução de boilerplate code |
| **Logging** | SLF4J | Sistema de logs |
| **Monitoramento** | Spring Actuator | Health checks e métricas |

---

## 🎯 Funcionalidades Core

### Para Produtores:
- ✅ Cadastro rápido no sistema
- ✅ Registro digital de perfil
- 🔄 Registro de lotes agrícolas (em desenvolvimento)
- 🔄 Dashboards de produtividade (em desenvolvimento)

### Para Gestores:
- ✅ Visualização de usuários cadastrados
- 🔄 Mapa interativo da produção (planejado)
- 🔄 Métricas de sustentabilidade (planejado)
- 🔄 Analytics de exportação (planejado)

### Para Compradores:
- 🔄 Consulta de rastreabilidade via QR Code (planejado)
- 🔄 Validação de origem e sustentabilidade (planejado)
- 🔄 Marketplace integrado (planejado)

**Legenda:**
- ✅ Implementado
- 🔄 Em desenvolvimento
- 📋 Planejado

---

## 📄 Licença

Este projeto foi desenvolvido como parte do **Challenge Oracle** na **FIAP**.

---
