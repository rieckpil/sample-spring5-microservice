package de.rieckpil.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.rieckpil.domain.Author;
import de.rieckpil.domain.Book;
import de.rieckpil.repositories.AuthorRepository;
import de.rieckpil.repositories.BookRepository;

public class Query implements GraphQLQueryResolver {
  
  private BookRepository bookRepository;
  private AuthorRepository authorRepository;

  public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Iterable<Author> findAllAuthors() {
    return authorRepository.findAll();
  }

  public long countBooks() {
    return bookRepository.count();
  }

  public long countAuthors() {
    return authorRepository.count();
  }
}
