# MecaniQA API

API REST da MecaniQA, desenvolvida em Java com Spring Boot. O projeto organiza as funcionalidades em controllers, services, repositories, models e DTOs. Os repositories acessam o MySQL usando JDBC.

## Requisitos

- JDK 21
- MySQL

## Configuração do banco

A aplicação espera o banco `MECANIQA` no MySQL local, porta `3307`. O script de criação das tabelas está em [`src/script_mecaniqa.sql`](src/script_mecaniqa.sql).

As credenciais são lidas destas variáveis de ambiente:

| Variável | Padrão |
| --- | --- |
| `DB_USER` | `root` |
| `DB_PASSWORD` | sem valor padrão; deve ser definida |

No PowerShell, configure-as antes de iniciar a aplicação:

```powershell
$env:DB_USER = "root"
$env:DB_PASSWORD = "sua_senha"
```

## Executar

```powershell
.\gradlew.bat bootRun
```

A API usa a porta `8081`.

## Estrutura do código

- `controllers`: endpoints HTTP e conversão das respostas.
- `service`: regras de negócio.
- `repository`: acesso e persistência dos dados.
- `model`: entidades usadas pela aplicação.
- `DTO`: objetos de entrada e saída da API.
- `mappers`: conversão entre DTOs, models e resultados JDBC.

O fluxo de uma requisição é `Controller → Service → Repository → MySQL`. A conexão JDBC é fornecida pelo Spring por meio de `DataSource`.

## Ordem de serviço

`OrdemServicoRepository` está sendo preparado para substituir a persistência em memória por JDBC, seguindo a interface genérica `Repository<OrdemServico, Long>`. A implementação dos métodos de persistência está em andamento.
