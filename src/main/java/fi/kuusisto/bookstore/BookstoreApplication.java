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
import fi.kuusisto.bookstore.domain.User;
import fi.kuusisto.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstoreDemo (BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository urepository) {
		return (args) -> {
			log.info("put books to a database");
			
			categoryRepository.save(new Category("Historia"));
			categoryRepository.save(new Category("Fantasia"));
			
			bookRepository.save(new Book("Vuonna 1984", "George Orwell", categoryRepository.findByName("Historia").get(0)));
			bookRepository.save(new Book("Harry Potter", "J.K.Rowling", categoryRepository.findByName("Fantasia").get(0)));	
			
			User user1 = new User("user", "$2a$10$RL.HKjDVC4uQlzw0uWv0FOE1/nlZWZN/flUYIWYNUNiIcLmJ8FjjS", "USER");
			User user2 = new User("admin", "$2a$10$cE6We18yeCWHoWt05CUEzekHWjAWj0EuJUwdSEYS0Dy0MBTm5hxyG", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("list all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};	
	}

}
