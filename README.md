# Verdantis (VITS - Visual Information Tracking System)

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen?style=flat-square&logo=springboot)
![Oracle](https://img.shields.io/badge/Oracle-Database-red?style=flat-square&logo=oracle)
![HATEOAS](https://img.shields.io/badge/REST-Level%203%20HATEOAS-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Sprint%202-yellow?style=flat-square)

## 📋 Sobre o Projeto

O **Verdantis** é um sistema de rastreabilidade e visualização inteligente desenvolvido para transformar o agronegócio brasileiro. O Brasil perde mais de **R$ 12 bilhões anuais** em exportações por falta de rastreabilidade e certificação sustentável. Nossa solução une **Oracle Database**, **Spring Boot** e tecnologias modernas para oferecer transparência, sustentabilidade e valor agregado aos produtos agrícolas.

### 🎯 Problema que Resolve

- **Falta de rastreabilidade**: Dificulta exportações e certificações sustentáveis
- **Gestão ineficiente**: Desperdício de água, energia e insumos
- **Baixa digitalização**: Pequenos produtores sem acesso a ferramentas modernas
- **Pressão internacional**: Mercados EUA/UE rejeitam produtos sem comprovação de origem

### 💡 Solução

Sistema de rastreabilidade agrícola com autenticação JWT, Flyway para versionamento de banco de dados e interface web.

## 📋 Funcionalidades

- ✅ **Autenticação JWT** com Spring Security (RSA)
- ✅ **Controle de Acesso** baseado em ROLE_PRODUTOR
- ✅ **Flyway** para versionamento do banco Oracle
- ✅ **Frontend HTML/CSS/JS** responsivo
- ✅ **API REST** para gerenciamento de:
  - Lotes
  - Propriedades
  - Certificações
  - Culturas
  - Usuários

## 🚀 Instalação e Execução

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Oracle Database 11g+

### Passo 1: Clonar o repositório

```bash
git clone https://github.com/joaoGFG/VitsAPI.git
cd vits-agrochain
```

### Passo 2: Configurar o banco de dados

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//seu-host:1521/ORCL
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Passo 3: Executar a aplicação

```bash
mvn spring-boot:run
```

Ou compilar e executar:

```bash
mvn clean install
java -jar target/vits-agrochain-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

## 🔐 Autenticação

### Endpoint de Login

**POST** `/login`

Request:
```json
{
  "email": "produtor@email.com",
  "password": "senha123"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "id": 1,
  "name": "João Silva",
  "email": "produtor@email.com",
  "role": "ROLE_PRODUTOR"
}
```

## 📊 Endpoints da API

### Usuários
- `GET /api/users` - Listar usuários (requer ROLE_PRODUTOR)
- `GET /api/users/{id}` - Obter usuário (requer ROLE_PRODUTOR)

### Lotes
- `GET /api/lotes` - Listar lotes
- `POST /api/lotes` - Criar lote
- `GET /api/lotes/{id}` - Obter lote
- `PUT /api/lotes/{id}` - Atualizar lote
- `DELETE /api/lotes/{id}` - Deletar lote

### Propriedades
- `GET /api/propriedades` - Listar propriedades
- `POST /api/propriedades` - Criar propriedade
- `GET /api/propriedades/{id}` - Obter propriedade
- `PUT /api/propriedades/{id}` - Atualizar propriedade
- `DELETE /api/propriedades/{id}` - Deletar propriedade

### Certificações
- `GET /api/certificacoes` - Listar certificações
- `POST /api/certificacoes` - Criar certificação
- `GET /api/certificacoes/{id}` - Obter certificação

### Culturas
- `GET /api/culturas` - Listar culturas
- `POST /api/culturas` - Criar cultura

## �️ Flyway - Versionamento do Banco

O Flyway gerencia automaticamente as migrações do banco de dados. As migrations estão em:

```
src/main/resources/db/migration/
```

### Estrutura de migrations

- `V1__initial_schema.sql` - Criação inicial das tabelas

### Como adicionar uma nova migration

1. Criar novo arquivo em `db/migration/`
2. Seguir padrão: `V{numero}__{descricao}.sql`
3. Exemplo: `V2__add_new_table.sql`

O Flyway executa **automaticamente** na inicialização da aplicação.

### Comandos Flyway (via Maven)

```bash
# Executar migrations
mvn flyway:migrate

```

## 🎨 Frontend

O frontend está em `src/main/resources/static/`

### Páginas

- **Home** - Página inicial com informações
- **Login** - Autenticação de usuários
- **Register** - Registro de novos usuários
- **Dashboard** - Painel de controle (requer autenticação)
- **Lotes** - Gerenciamento de lotes
- **Propriedades** - Gerenciamento de propriedades
- **Certificações** - Visualização de certificações
- **Culturas** - Gerenciamento de culturas

### Acessando o Frontend

Após iniciar a aplicação, acesse: `http://localhost:8080`

### Tecnologias Frontend

- HTML5
- CSS3 (Responsivo)
- JavaScript vanilla (sem frameworks)
- Fetch API para comunicação

## 🔑 Segurança

### JWT (JSON Web Token)

- **Algoritmo**: RS256 (RSA)
- **Chaves**: `src/main/resources/certs/`
  - `private_key.pem` - Chave privada (assinar tokens)
  - `public_key.pem` - Chave pública (validar tokens)
- **Expiração**: 24 horas

### Spring Security

- CSRF desabilitado (API REST)
- CORS habilitado para localhost
- Sessões stateless
- Role-based access control (RBAC)

### CORS

Configurado para aceitar requisições de `*` (desenvolvimento). 
**Mudar em produção** em `SecurityConfig.java`

## 📦 Estrutura do Projeto

```
vits-agrochain/
├── src/
│   ├── main/
│   │   ├── java/br/com/vits/orc/vits_agrochain/
│   │   │   ├── config/          # Configurações (Security, RSA)
│   │   │   ├── controller/      # Controllers REST
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositórios
│   │   │   ├── service/         # Lógica de negócio
│   │   │   └── dto/             # Data Transfer Objects
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/          # Frontend (HTML/CSS/JS)
│   │       ├── db/migration/    # Flyway migrations
│   │       └── certs/           # Chaves RSA
│   └── test/
├── pom.xml
└── README.md
```

## 🛠️ Tecnologias

- **Framework**: Spring Boot 3.3.5
- **Linguagem**: Java 17
- **ORM**: JPA/Hibernate
- **Banco de Dados**: Oracle
- **Autenticação**: Spring Security + JWT (RSA)
- **Validação**: Jakarta Validation
- **Versioning DB**: Flyway
- **Documentação API**: Springdoc OpenAPI (Swagger)

## 📖 Swagger/OpenAPI

A documentação interativa da API está disponível em:

```
http://localhost:8080/swagger-ui.html
```

## 🐛 Troubleshooting

### Erro de conexão com Oracle

Verifique:
- URL de conexão está correta
- Credenciais estão corretas
- Oracle está rodando
- Porta 1521 está aberta

### Erro ao executar Flyway

```bash
# Verificar se tabelas estão criadas manualmente
# Se sim, executar baseline:
mvn flyway:baseline
```

### Erro 401 (Não Autorizado)

- Verifique se o token JWT foi passado no header `Authorization`
- Verifique se o token não expirou
- Verifique se o token é válido

## 👤 Padrão de Usuário

Ao registrar, todo novo usuário recebe automaticamente `ROLE_PRODUTOR`.

Exemplos de acesso:
- ✅ Produtores podem acessar `/api/**`
- ❌ Usuários não autenticados recebem 401
- ❌ Usuários com role diferente recebem 403

## 🔄 Fluxo de Login

1. Usuário faz POST em `/login` com email e senha
2. Spring Security autentica as credenciais
3. TokenService gera JWT com claims (id, email, role)
4. Token é armazenado localmente no browser
5. Requisições futuras incluem `Authorization: Bearer {token}`
6. SecurityConfig valida o token via JWT decoder

## 🚢 Deploy

### Produção

1. Mudar `CORS` em `SecurityConfig.java`
2. Gerar novo keystore RSA
3. Usar variáveis de ambiente para credenciais
4. Habilitar HTTPS
5. Configurar banco de produção


Uma plataforma integrada de rastreabilidade digital que conecta produtores, distribuidores e compradores, permitindo registro, visualização e certificação de cadeias produtivas agrícolas.

---

### PDF da documentação completa em /documentation

---

## 🆕 Evolução Sprint 1 → Sprint 2

### **Principais Acréscimos da Sprint 2**

#### 🔗 **1. HATEOAS - Nível 3 de Maturidade REST (Richardson)**

A principal evolução da Sprint 2 foi a implementação de **HATEOAS** (Hypermedia as the Engine of Application State), elevando a API para o **Nível 3 de Maturidade REST** proposto por Leonard Richardson.

**O que mudou:**

- ✅ **PropertyController** agora retorna `EntityModel` e `PagedModel` com links hipermídia
- ✅ Endpoints com paginação incluem links de navegação (`first`, `self`, `next`, `last`)
- ✅ Cada recurso possui link `self` apontando para sua própria URL
- ✅ Cliente pode navegar pela API sem conhecer previamente a estrutura das URLs

**Antes (Sprint 1 - Nível 2):**
```json
{
  "propertyId": 1,
  "propertyName": "Fazenda São José",
  "totalArea": "150 hectares"
}
```

**Depois (Sprint 2 - Nível 3 com HATEOAS):**
```json
{
  "propertyId": 1,
  "propertyName": "Fazenda São José",
  "totalArea": "150 hectares",
  "_links": {
    "self": {
      "href": "http://localhost:8080/properties/1"
    }
  }
}
```

#### 📄 **2. Paginação Avançada**

Implementação de paginação completa com Spring Data:

- ✅ Suporte a parâmetros `page`, `size` e `sort`
- ✅ Metadados de paginação (total de elementos, páginas, página atual)
- ✅ Links de navegação entre páginas
- ✅ Ordenação customizável por qualquer campo

**Exemplo de uso:**
```http
GET /properties?page=0&size=10&sort=propertyName,asc
```

**Response com paginação:**
```json
{
  "_embedded": {
    "propertyList": [ /* ... */ ]
  },
  "_links": {
    "first": { "href": "..." },
    "self": { "href": "..." },
    "next": { "href": "..." },
    "last": { "href": "..." }
  },
  "page": {
    "size": 10,
    "totalElements": 25,
    "totalPages": 3,
    "number": 0
  }
}
```

#### 🏗️ **3. Novos Domínios Implementados**

Expansão significativa das entidades do sistema:

**Entidades Adicionadas:**
- ✅ `Property` (Propriedade Rural) - Gestão de propriedades agrícolas
- ✅ `Lot` (Lote) - Rastreabilidade de lotes de produção

**Controllers com CRUD Completo:**
- ✅ `PropertyController` - CRUD de propriedades com HATEOAS
- ✅ `LotController` - Gestão de lotes de produção

#### 🔧 **4. Melhorias Técnicas**

**Dependências Atualizadas:**
- ✅ **Spring Boot** atualizado para `3.3.5` (estabilidade)
- ✅ **Spring HATEOAS** adicionado para suporte hipermídia
- ✅ **SpringDoc OpenAPI** mantido para documentação Swagger

**Arquitetura:**
- ✅ Padrão **EntityModel/PagedModel** para responses HATEOAS
- ✅ Método `toEntityModel()` nas entidades para centralizar criação de links
- ✅ Uso de `PagedResourcesAssembler` para paginação automática

#### 🧪 **7. Documentação e Testes**

**Melhorias na Documentação:**
- ✅ Coleção Postman atualizada com testes HATEOAS
- ✅ Exemplos de paginação e ordenação
- ✅ Documentação de responses com links hipermídia
- ✅ Swagger UI integrado com HATEOAS

**Arquivo de Testes:**
- ✅ Coleção do postman no Readme
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
| **Backend** | Java 17 | Linguagem de programação |
| **Framework** | Spring Boot 3.3.5 | Framework para API REST |
| **Persistência** | Spring Data JPA | Camada de acesso a dados |
| **Banco de Dados** | Oracle Database | Armazenamento principal |
| **Driver JDBC** | ojdbc11 | Conexão com Oracle |
| **Validação** | Hibernate Validator | Validação de dados |
| **Hipermídia** | Spring HATEOAS | Links HATEOAS (Nível 3 REST) ⭐ **NOVO Sprint 2** |
| **Documentação** | SpringDoc OpenAPI | Swagger UI e documentação API |
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
