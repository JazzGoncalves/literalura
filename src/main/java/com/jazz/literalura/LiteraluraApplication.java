package com.jazz.literalura;

import com.jazz.literalura.service.CatalogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final CatalogService service;

    public LiteraluraApplication(CatalogService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== LiterAlura ===");
            System.out.println("1) Buscar livro por título (API → salvar)");
            System.out.println("2) Listar todos os livros");
            System.out.println("3) Listar livros por idioma");
            System.out.println("4) Contar livros por idioma");
            System.out.println("5) Sair");
            System.out.print("Opção: ");
            String op = sc.nextLine().trim();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("Título: ");
                        var title = sc.nextLine();
                        var saved = service.searchAndSaveFirst(title);
                        System.out.println(saved.map(b -> "Salvo: " + b).orElse("Nada encontrado."));
                    }
                    case "2" -> {
                        var list = service.listAllBooks();
                        if (list.isEmpty()) System.out.println("Sem livros.");
                        else list.forEach(System.out::println);
                    }
                    case "3" -> {
                        System.out.print("Idioma (ex.: en, pt, es): ");
                        var lang = sc.nextLine();
                        var list = service.listBooksByLanguage(lang);
                        if (list.isEmpty()) System.out.println("Sem livros no idioma " + lang);
                        else list.forEach(System.out::println);
                    }
                    case "4" -> {
                        System.out.print("Idioma (ex.: en, pt, es): ");
                        var lang = sc.nextLine();
                        long count = service.countBooksByLanguage(lang);
                        System.out.println("Total: " + count);
                    }
                    case "5" -> { System.out.println("Tchau!"); return; }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
