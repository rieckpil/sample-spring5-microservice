package de.rieckpil;

import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableAsync;
import de.rieckpil.domain.Author;
import de.rieckpil.domain.Book;
import de.rieckpil.repositories.AuthorRepository;
import de.rieckpil.repositories.BookRepository;
import de.rieckpil.resolver.BookResolver;
import de.rieckpil.resolver.Mutation;
import de.rieckpil.resolver.Query;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableAsync
public class SampleMicroserviceSpring5Application {

  public static void main(String[] args) {
    SpringApplication.run(SampleMicroserviceSpring5Application.class, args);
  }

  @Bean("dataSource")
  @Profile("default")
  @Primary
  public DataSource standaloneDefaultDataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:scripts/h2-schema.sql").build();
  }

  @Bean
  public MeterRegistryCustomizer<MeterRegistry> commonTags() {
    return (registry) -> registry.config().commonTags("application", "webinar");
  }

  @Bean
  public TaskExecutor taskExecutor() {
    return new SimpleAsyncTaskExecutor();
  }

  @Bean
  public BookResolver authorResolver(AuthorRepository authorRepository) {
    return new BookResolver(authorRepository);
  }

  @Bean
  public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
    return new Query(authorRepository, bookRepository);
  }

  @Bean
  public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
    return new Mutation(authorRepository, bookRepository);
  }

  @Bean
  public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
    return (args) -> {
      Author author = new Author().withFirstName("Herbert").withLastName("Schildt");
      authorRepository.save(author);
      bookRepository.save(new Book().withTitle("Java: A Beginner's Guide, Sixth Edition")
          .withIsbn("0071809252").withPageCount(728).withAuthor(author));
    };
  }
}
