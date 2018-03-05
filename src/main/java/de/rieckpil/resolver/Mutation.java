package de.rieckpil.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.rieckpil.domain.Author;
import de.rieckpil.domain.Book;
import de.rieckpil.repositories.AuthorRepository;
import de.rieckpil.repositories.BookRepository;

public class Mutation implements GraphQLMutationResolver {

  private BookRepository bookRepository;
  private AuthorRepository authorRepository;

  public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  public Author newAuthor(String firstName, String lastName) {
    Author author = new Author();
    author.setFirstName(firstName);
    author.setLastName(lastName);
    authorRepository.save(author);
    return author;
  }

  public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
    Book book = new Book();
    Author author = new Author();
    author.setId(authorId);
    book.setAuthor(author);
    book.setTitle(title);
    book.setIsbn(isbn);
    book.setPageCount(pageCount != null ? pageCount : 0);
    bookRepository.save(book);
    return book;
  }

  public boolean deleteBook(Long id) {
    bookRepository.deleteById(id);
    return true;
  }

  public Book updateBookPageCount(Integer pageCount, Long id) {
    Book book = bookRepository.getOne(id);
    book.setPageCount(pageCount);
    bookRepository.save(book);
    return book;
  }

}
