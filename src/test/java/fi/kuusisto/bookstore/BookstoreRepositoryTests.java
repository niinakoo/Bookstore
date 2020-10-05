package fi.kuusisto.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.kuusisto.bookstore.domain.Book;
import fi.kuusisto.bookstore.domain.BookRepository;
import fi.kuusisto.bookstore.domain.Category;
import fi.kuusisto.bookstore.domain.CategoryRepository;
import fi.kuusisto.bookstore.domain.User;
import fi.kuusisto.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTests {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = bookRepository.findByTitle("Harry Potter");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("J.K.Rowling");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Hobitti", "J.R.R. Tokien", new Category("Fantasia"));
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = categoryRepository.findByName("Fiktio");
		assertThat(categories).hasSize(0);
	}
	
	@Test
	public void createNewUser() {
		User user = new User("käyttäjä", "$2a$10$rpC49rd9361yakxwigCRLOGKce5tutpsGqU1S48nnL9aqYwDPDrye", "KÄYTTÄJÄ");
		urepository.save(user);
	}
	
}
