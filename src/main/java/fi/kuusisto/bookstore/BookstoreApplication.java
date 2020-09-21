package fi.kuusisto.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstoreDemo (BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("put books to a database");
			
			categoryRepository.save(new Category("Historia"));
			categoryRepository.save(new Category("Fantasia"));
			
			bookRepository.save(new Book("Vuonna 1984", "George Orwell", categoryRepository.findByName("Historia").get(0)));
			bookRepository.save(new Book("Harry Potter", "J.K.Rowling", categoryRepository.findByName("Fantasia").get(0)));	
			
			log.info("list all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};	
	}

}
