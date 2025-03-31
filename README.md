# Controle de Servidor Público - Docker Compose

Este repositório contém a configuração necessária para rodar a aplicação utilizando Quarkus, com os serviços auxiliares de MinIO e PostgreSQL.

## Dados da Inscrição

- Nome: `Herberton Lauro`
- Telefone: `(65) 981180447`
- Email: `herberton.dev@gmail.com`
- Vaga: `Desenvolvedor Backend junior`

## 📌 Pré-requisitos

Antes de rodar os containers, verifique se você possui as seguintes ferramentas instaladas no seu sistema:

- **Docker**: [Instalar Docker](https://www.docker.com/get-started)
- **Docker Compose**: [Instalar Docker Compose](https://docs.docker.com/compose/install/)

## 📂 Estrutura do Docker Compose

O arquivo `docker-compose.yml` define três serviços principais:

1. **MinIO** - Armazenamento de objetos.
2. **PostgreSQL** - Banco de dados PostgreSQL com inicialização via script SQL.
3. **Servidor API (Quarkus)** - API para controle de servidores, que depende do PostgreSQL e MinIO.

---

## ⚙️ Serviços Definidos

### 🗂 MinIO (Armazenamento de Objetos)
- **Imagem**: `quay.io/minio/minio:latest`
- **Porta**: `9000`
- **Credenciais**:
    - Usuário: `admin`
    - Senha: `admin123`

---

### 🛢 PostgreSQL (Banco de Dados)
- **Imagem**: `postgres:latest`
- **Porta**: `5432`
- **Credenciais**:
    - Usuário: `admin`
    - Senha: `admin`
    - Banco de dados: `seletivoseplagmt`
- **Script de Inicialização**: `init.sql`
- **Modo de Rede**: `host`

---


# ▶️ Passos para Rodar a Aplicação

## 1️⃣ Clonar o Repositório

```sh
git clone <URL_DO_REPOSITORIO>
cd <DIRETORIO_DO_REPOSITORIO>
```

## 2️⃣ Subir os Containers

```sh
docker-compose up
```
- **Nota**: Para rodar em segundo plano, use `docker-compose up -d`.

## 3️⃣ Acessar o Swagger UI

- Com os containers em execução, abra o navegador e acesse a interface Swagger para explorar e testar a API:
    - [Swagger UI](http://localhost:8080/q/swagger-ui/)
- **Dica**: Caso a interface não carregue, verifique se o serviço Quarkus subiu corretamente com `docker-compose logs`.


## ▶️ Testando a Aplicação

1. Explore os endpoints disponíveis para gerenciar servidores públicos.
2. Utilize o MinIO em [http://localhost:9000](http://localhost:9000) (login: `admin` / senha: `admin123`) para visualizar ou gerenciar arquivos, se necessário.

## ⏹️ Parar a Aplicação

- Para encerrar os serviços, use:
  ```sh
  docker-compose down
  ```
- Para remover os volumes (dados persistentes), adicione a flag `--volumes`:
  ```sh
  docker-compose down --volumes
  ```

## 📋 Notas Adicionais

- Certifique-se de que as portas `8080`, `9000` e `5432` estejam livres antes de subir os containers.
- O script `init.sql` inicializa o banco de dados automaticamente ao subir o PostgreSQL.
- Em caso de erros, consulte os logs com:
  ```sh
  docker-compose logs <nome_do_serviço>
  ```
  Exemplo: `docker-compose logs quarkus`, `docker-compose logs minio`, `docker-compose logs postgres`.


## 📦 DockerHub

Para facilitar o uso da aplicação, a imagem do projeto também está disponível no DockerHub. Você pode baixar a imagem e rodar a aplicação sem precisar configurar o ambiente localmente.

### 🛠 Puxar a Imagem

```sh
docker pull herbertonlauro/seletivoseplagmt
