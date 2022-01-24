package uni.book_project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBybookGenreStartsWithIgnoreCase(String lastName);
}