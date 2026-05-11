# Galeria Seleta

Aplicação web full-stack para a **Galeria Seleta**, um brechó online com curadoria de peças vintage e exclusivas.

- **Frontend:** Angular 19 com SSR (Server-Side Rendering)
- **Backend:** Java 17 + Spring Boot 3 + SQLite

---

## Pré-requisitos

Antes de começar, instale:

| Ferramenta | Versão mínima | Download |
|---|---|---|
| Node.js | 18+ | https://nodejs.org |
| Java (JDK) | 17+ | https://adoptium.net |

> O Maven (ferramenta de build do Java) **não precisa ser instalado** — o projeto baixa automaticamente na primeira execução.

---

## Como rodar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/GustAndrade07/BackEnd_GaleriaSeleta.git
cd BackEnd_GaleriaSeleta
```

### 2. Rode o backend (Spring Boot)

Abra um terminal na pasta `backend/` e execute:

```bash
cd backend
mvnw.cmd spring-boot:run
```

Na **primeira execução**, o script baixa o Maven automaticamente. Aguarde até aparecer:

```
Started GaleriaSelataApplication in X seconds
```

O banco de dados (`galeria_seleta.db`) é criado automaticamente nessa etapa.
A API ficará disponível em `http://localhost:8080`.

### 3. Rode o frontend (Angular)

Abra **outro terminal** na raiz do projeto e execute:

```bash
npm install
npm start
```

Acesse `http://localhost:4200/` no navegador.

---

## Estrutura do projeto

```
BackEnd_GaleriaSeleta/
├── backend/                  # API REST em Spring Boot
│   ├── src/
│   │   └── main/
│   │       ├── java/         # Controllers, Services, Repositories, Models
│   │       └── resources/
│   │           ├── application.properties
│   │           └── schema.sql   # Estrutura do banco de dados
│   ├── mvnw.cmd              # Script para rodar o Maven no Windows
│   └── pom.xml               # Dependências Java
│
├── src/                      # Aplicação Angular
│   └── app/
│       ├── core/             # Models e dados mock
│       └── [componentes]/    # home, produtos, login, cadastro, etc.
│
├── package.json              # Dependências Node.js
└── README.md
```

---

## Páginas do frontend

| Rota | Descrição |
|---|---|
| `/` | Home com carrossel e vitrine de novidades |
| `/produtos` | Catálogo com filtro por categoria e ordenação |
| `/login` | Autenticação de usuário |
| `/cadastro` | Criação de conta |
| `/sobre` | Sobre a Galeria Seleta |

## Endpoints da API

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/produtos` | Listar produtos |
| GET | `/api/produtos/{id}` | Buscar produto por ID |
| GET | `/api/categorias` | Listar categorias |
| POST | `/api/auth/login` | Login |
| POST | `/api/auth/register` | Cadastro |
| GET | `/api/carrinho` | Ver carrinho |
| POST | `/api/pedidos` | Criar pedido |

> A documentação completa dos endpoints está nos arquivos de controller em `backend/src/main/java/com/galeriaseleta/controller/`.
