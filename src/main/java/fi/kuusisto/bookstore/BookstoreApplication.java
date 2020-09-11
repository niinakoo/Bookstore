package fi.kuusisto.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.kuusisto.bookstore.domain.Book;
import fi.kuusisto.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstoreDemo (BookRepository repository) {
		return (args) -> {
			System.out.println("put books to database, in this case h2");
			repository.save(new Book("Vuonna 1984", "George Orwell"));
			repository.save(new Book("Harry Potter", "J.K.Rowling"));	
			
			System.out.println("list all books");
			for (Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};	
	}
}
