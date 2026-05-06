# Galeria Seleta — Front-end

Aplicação web desenvolvida com **Angular 19** para a Galeria Seleta, um brechó online com curadoria de peças vintage e exclusivas. O projeto conta com renderização no servidor (SSR) via Angular SSR + Express.

## Tecnologias

- Angular 19 (standalone components)
- Angular SSR + Express
- TypeScript 5.7
- Karma + Jasmine (testes unitários)

## Pré-requisitos

- Node.js 18+
- Angular CLI 19

```bash
npm install -g @angular/cli
```

## Instalação

```bash
npm install
```

## Executando localmente

```bash
npm start
```

Acesse `http://localhost:4200/`. A aplicação recarrega automaticamente ao salvar arquivos.

## Build de produção

```bash
npm run build
```

Os artefatos gerados ficam em `dist/`. O build de produção já aplica otimizações de performance.

## SSR (Server-Side Rendering)

```bash
npm run build
node dist/galeria-seleta/server/server.mjs
```

## Testes

```bash
npm test
```

## Estrutura de páginas

| Rota | Componente | Descrição |
|---|---|---|
| `/` | `HomeComponent` | Hero com carrossel e vitrine de novidades |
| `/produtos` | `ProdutosComponent` | Catálogo com filtro por categoria e ordenação |
| `/login` | `LoginComponent` | Autenticação de usuário |
| `/cadastro` | `CadastroComponent` | Criação de conta |
| `/sobre` | `SobreComponent` | Sobre a Galeria Seleta |

## Modelo de Produto

```ts
interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  preco_desconto: number | null;
  imagem_url: string;
  categoria: string;
  status: 'ativo' | 'inativo';
  criado_em: string;
}
```
