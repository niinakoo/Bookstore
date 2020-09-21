package fi.kuusisto.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.kuusisto.bookstore.domain.Book;
import fi.kuusisto.bookstore.domain.BookRepository;
import fi.kuusisto.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/index")
	@ResponseBody
	public String giveIndexPage() {
		return "Index!";
	}
	
	//Show all books
	@GetMapping("/booklist")
	public String returnAllBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	//Add new book
	@GetMapping("/add")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addBook";
	}
	
	//Save new book
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}
	
	//Delete book
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    } 
	
	//Edit book
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}
	
	//REST service that returns all books (JSON)
	@RequestMapping(value="books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	//REST service that returns one book by id (JSON)
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
	return bookRepository.findById(bookId);
	}

}
