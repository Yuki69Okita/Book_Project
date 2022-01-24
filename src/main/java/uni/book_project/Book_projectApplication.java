package uni.book_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Book_projectApplication {

	private static final Logger log = LoggerFactory.getLogger(Book_projectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Book_projectApplication.class);
	}

	@Bean
	public CommandLineRunner loadData(BookRepository repository) {
		return (args) -> {
			// save a couple of books
			repository.save(new Book("Harry Potter and the Half-Blood Prince", "Fantasy"));
			repository.save(new Book("Assassin's Creed", "Fantasy"));
			repository.save(new Book("A Tale of Two Cities", "Historical novel"));
			repository.save(new Book("Invisible Man", "Bildungsroman"));
		};
	}

}
