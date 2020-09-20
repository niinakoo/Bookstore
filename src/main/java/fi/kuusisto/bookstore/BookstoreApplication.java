package fi.kuusisto.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.kuusisto.bookstore.domain.Book;
import fi.kuusisto.bookstore.domain.BookRepository;
import fi.kuusisto.bookstore.domain.Category;
import fi.kuusisto.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstoreDemo (BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			System.out.println("put books to database, in this case h2");
			
			categoryRepository.save(new Category("Historia"));
			categoryRepository.save(new Category("Fantasia"));
			
			bookRepository.save(new Book("Vuonna 1984", "George Orwell", categoryRepository.findByName("Historia").get(0)));
			bookRepository.save(new Book("Harry Potter", "J.K.Rowling", categoryRepository.findByName("Fantasia").get(0)));	
			
			System.out.println("list all books");
			for (Book book : bookRepository.findAll()) {
				System.out.println(book.toString());
			}
		};	
	}

}
