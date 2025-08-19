# LiterAlura - Catálogo de Livros

Este é um projeto em **Java 17** com **Spring Boot 3**, que integra com a API **Gutendex**
para buscar e cadastrar livros em um banco de dados.  
Atualmente, o projeto está configurado para usar **H2 Database (em memória)**,
o que facilita os testes sem necessidade de instalar PostgreSQL ou outro SGBD.

---

## 🚀 Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database (em memória)
- Maven

---

## 📌 Funcionalidades
- Buscar livro por título (via API Gutendex)
- Listar livros cadastrados no banco
- Menu interativo no console

---

## ⚙️ Requisitos
- JDK 17+
- Maven 3+

---

## ▶️ Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/literalura.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd literalura
   ```

3. Compile e rode com Maven:
   ```bash
   mvn spring-boot:run
   ```

4. O menu será exibido no console:
   ```
   === LiterAlura ===
   1) Buscar livro por título (API)
   2) Listar livros (do banco)
   3) Sair
   ```

---

## 📝 Observações
- O banco H2 é **em memória**. Sempre que reiniciar o projeto, os dados são apagados.
- Para persistência real, basta configurar PostgreSQL ou outro banco no `application.properties`.

---

## 📄 Licença
Projeto desenvolvido para fins de estudo. Uso livre!
