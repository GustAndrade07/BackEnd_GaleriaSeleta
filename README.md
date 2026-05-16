# Galeria Seleta

Aplicação web full-stack para a **Galeria Seleta**, um brechó online com curadoria de peças vintage e exclusivas.

- **Frontend:** Angular 19 com SSR (Server-Side Rendering)
- **Backend:** Java 17 + Spring Boot 3 + Spring Security + JWT + SQLite

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
.\mvnw.cmd spring-boot:run
```

Na **primeira execução**, o script baixa o Maven automaticamente. Aguarde até aparecer:

```
Started GaleriaSelataApplication in X seconds
```

O banco de dados (`galeria_seleta.db`) é criado automaticamente na raiz de `backend/`.
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
├── backend/                        # API REST em Spring Boot
│   ├── src/main/java/com/galeriaseleta/
│   │   ├── config/                 # CORS, Spring Security e tratamento global de erros
│   │   ├── controller/             # Endpoints REST
│   │   ├── converter/              # Conversor LocalDateTime para SQLite
│   │   ├── dto/                    # Data Transfer Objects
│   │   │   ├── request/            # DTOs de entrada (dados recebidos da API)
│   │   │   └── response/           # DTOs de saída (dados retornados pela API)
│   │   ├── model/                  # Entidades JPA
│   │   ├── repository/             # Repositórios Spring Data JPA
│   │   ├── security/               # JWT: filtro, utilitário e UserDetailsService
│   │   └── service/                # Regras de negócio
│   ├── src/main/resources/
│   │   ├── application.properties  # Configuração do banco e servidor
│   │   └── schema.sql              # Criação das tabelas do banco
│   ├── mvnw.cmd                    # Maven Wrapper para Windows
│   └── pom.xml                     # Dependências Java
│
├── src/                            # Aplicação Angular
│   └── app/
│       ├── core/                   # Models e dados mock
│       └── [componentes]/          # home, produtos, login, cadastro, etc.
│
├── teste.http                      # Arquivo de testes da API (REST Client VSCode)
├── package.json                    # Dependências Node.js
└── README.md
```

---

## Endpoints da API

Base URL: `http://localhost:8080/api`

### Autenticação
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| POST | `/auth/register` | Cadastrar usuário — retorna JWT | Público |
| POST | `/auth/login` | Login — retorna JWT | Público |
| POST | `/auth/logout` | Logout (client-side) | Público |
| POST | `/auth/forgot-password` | Recuperação de senha | Público |
| POST | `/auth/refresh` | Renovar token | Público |

**Resposta do login/register:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tipo": "Bearer",
  "usuario": {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "role": "ROLE_USER"
  }
}
```

Para acessar rotas protegidas, envie o token no header:
```
Authorization: Bearer <token>
```

### Usuário
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| GET | `/usuarios/me` | Ver perfil | Autenticado |
| PUT | `/usuarios/me` | Atualizar perfil | Autenticado |
| PATCH | `/usuarios/me/senha` | Alterar senha | Autenticado |
| DELETE | `/usuarios/me` | Deletar conta | Autenticado |
| GET | `/usuarios/me/enderecos` | Listar endereços | Autenticado |
| POST | `/usuarios/me/enderecos` | Adicionar endereço | Autenticado |
| DELETE | `/usuarios/me/enderecos/{id}` | Remover endereço | Autenticado |

### Produtos
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| GET | `/produtos` | Listar produtos (filtros: `ordenacao`, `status`) | Público |
| GET | `/produtos/{id}` | Buscar por ID | Público |
| GET | `/produtos/novidades` | Listar novidades | Público |
| GET | `/produtos/busca?termo=` | Buscar por nome | Público |
| POST | `/produtos` | Criar produto | Admin |
| PUT | `/produtos/{id}` | Atualizar produto | Admin |
| PATCH | `/produtos/{id}/status` | Alterar status | Admin |
| DELETE | `/produtos/{id}` | Deletar produto | Admin |

### Categorias
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| GET | `/categorias` | Listar categorias | Público |
| GET | `/categorias/{id}` | Buscar por ID | Público |
| GET | `/categorias/{id}/produtos` | Produtos de uma categoria | Público |
| POST | `/categorias` | Criar categoria | Admin |
| PUT | `/categorias/{id}` | Atualizar categoria | Admin |
| DELETE | `/categorias/{id}` | Deletar categoria | Admin |

### Carrinho
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| GET | `/carrinho` | Ver carrinho | Autenticado |
| POST | `/carrinho/itens` | Adicionar item | Autenticado |
| PUT | `/carrinho/itens/{id}` | Atualizar quantidade | Autenticado |
| DELETE | `/carrinho/itens/{id}` | Remover item | Autenticado |
| DELETE | `/carrinho` | Limpar carrinho | Autenticado |

### Pedidos
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| GET | `/pedidos` | Listar pedidos (filtro: `status`) | Autenticado |
| GET | `/pedidos/{id}` | Buscar por ID | Autenticado |
| POST | `/pedidos` | Criar pedido (checkout) | Autenticado |
| PATCH | `/pedidos/{id}/cancelar` | Cancelar pedido | Autenticado |
| PATCH | `/pedidos/{id}/status` | Atualizar status | Admin |

### Contato e Newsletter
| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| POST | `/contato` | Enviar mensagem de contato | Público |
| POST | `/newsletter/inscrever` | Inscrever e-mail | Público |
| DELETE | `/newsletter/cancelar?email=` | Cancelar inscrição | Público |

---

## Tratamento de erros

A API retorna respostas HTTP adequadas para cada situação:

| Código | Situação |
|---|---|
| `200` / `201` / `204` | Sucesso |
| `400` | Dados inválidos |
| `401` | Credenciais incorretas |
| `404` | Recurso não encontrado |
| `409` | Conflito (ex: e-mail já cadastrado) |
| `500` | Erro interno |

---

## Banco de dados

O banco SQLite é criado automaticamente em `backend/galeria_seleta.db` na primeira execução. As tabelas são definidas em `backend/src/main/resources/schema.sql`.

| Tabela | Descrição |
|---|---|
| `usuarios` | Contas de usuário (campo `role`: `ROLE_USER` ou `ROLE_ADMIN`) |
| `categorias` | Categorias de produtos (suporta hierarquia) |
| `produtos` | Catálogo de peças |
| `fotos_produto` | Imagens dos produtos |
| `carrinho` | Itens no carrinho por usuário |
| `pedidos` | Pedidos realizados |
| `itens_pedido` | Produtos de cada pedido |
| `enderecos` | Endereços de entrega por usuário |
| `cupons` | Cupons de desconto |
| `opcoes_frete` | Opções de envio |
| `pagamentos` | Dados de pagamento |
| `contatos` | Mensagens do formulário de contato |
| `newsletter` | Inscrições de e-mail |

---

## Testando a API

O arquivo [`teste.http`](teste.http) contém 56 requisições cobrindo todos os endpoints. Para usar, instale a extensão **REST Client** no VSCode (Huachao Mao) e clique em **Send Request** acima de cada requisição.

---

## Segurança

Autenticação via **JWT (JSON Web Token)** com Spring Security 6. O token expira em 24 horas.

| Role | Descrição |
|---|---|
| `ROLE_USER` | Usuário comum. Atribuído automaticamente no cadastro. |
| `ROLE_ADMIN` | Administrador. Acesso total ao gerenciamento do catálogo. |

---

## Pendente

- Refresh token
- Recuperação e redefinição de senha (envio de e-mail)
- Blacklist de tokens no logout
- Endpoint dedicado para criação de administrador
- Integração frontend ↔ API
- Painel administrativo
- Processamento de pagamento
