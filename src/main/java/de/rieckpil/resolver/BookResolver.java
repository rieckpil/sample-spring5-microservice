package de.rieckpil.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.rieckpil.domain.Author;
import de.rieckpil.domain.Book;
import de.rieckpil.repositories.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
  
  private AuthorRepository authorRepository;

  public BookResolver(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author getAuthor(Book book) {
    return authorRepository.getOne(book.getAuthor().getId());
  }
}
