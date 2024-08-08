# SimpleSchedulerAPI

Uma API para gerenciar agendamentos e compromissos. Oferece funcionalidades básicas para criar, excluir e consultar agendamentos.

## Funcionalidades

- **Criar Agendamento:** Adicione novos agendamentos com data, hora e descrição.
- **Excluir Agendamento:** Remova agendamentos existentes.
- **Consultar Agendamentos:** Liste todos os agendamentos ou busque por detalhes específicos.

## Instalação

### Requisitos

- Java 21
- Maven
- Banco de dados (configuração padrão para H2)

### Configuração
1. **Clone o Repositório:**

```bash
git clone https://github.com/jfsog/SimpleSchedulerAPI.git
cd SimpleSchedulerAPI
```
2. **Instale as Dependências:**

```bash
mvn install
```
3. **Execute a Aplicação:**

```bash
mvn spring-boot:run
```
## Uso

### Endpoints

#### Criar Agendamento
- **Método:** `POST`
- **URL:** `/`
- **Descrição:** Cria um novo agendamento com data, hora de início, duração e descrição.
- **Corpo da Requisição:**
```json 
  {
    "dataHoraInicio": "2024-08-10T10:00:00",
    "duracao": 60,
    "descricao": "Reunião com equipe"
  }
   ```
#### Exclui Agendamento

- **Método:** `GET`
- **URL:** `/remover`
- **Descrição:** Remove um agendamento existente pelo ID.
- **Parâmetros de URL:** `id` (obrigatório): ID do agendamento a ser excluído.


#### Consultar Agendamentos

-   **Método:** `GET`
-   **URL:** `/`
-   **Descrição:** Lista todos os agendamentos ou busca por detalhes específicos.
```json
[
  {
    "id": 1,
    "dataHoraInicio": "2024-08-10T10:00:00",
    "duracao": 60,
    "descricao": "Reunião com equipe"
  },
  {
    "id": 2,
    "dataHoraInicio": "2024-08-11T14:00:00",
    "duracao": 30,
    "descricao": "Entrevista"
  }
]
