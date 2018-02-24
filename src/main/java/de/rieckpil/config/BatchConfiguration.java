package de.rieckpil.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import de.rieckpil.batch.CustomerItemProcessor;
import de.rieckpil.batch.JobCompletionNotificationListener;
import de.rieckpil.domain.Customer;
import de.rieckpil.dtos.CustomerDTO;
import de.rieckpil.repositories.CustomerRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  public CustomerRepository customerRepository;

  @Bean
  public FlatFileItemReader<CustomerDTO> reader() {
    FlatFileItemReader<CustomerDTO> reader = new FlatFileItemReader<CustomerDTO>();
    reader.setResource(new ClassPathResource("customer-data.csv"));
    reader.setLineMapper(new DefaultLineMapper<CustomerDTO>() {
      {
        setLineTokenizer(new DelimitedLineTokenizer() {
          {
            setNames(new String[] {"firstName", "lastName", "crmId"});
          }
        });
        setFieldSetMapper(new BeanWrapperFieldSetMapper<CustomerDTO>() {
          {
            setTargetType(CustomerDTO.class);
          }
        });
      }
    });
    return reader;
  }

  @Bean
  public CustomerItemProcessor processor() {
    return new CustomerItemProcessor();
  }

  @Bean
  public RepositoryItemWriter<Customer> writer() {
    RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<Customer>();
    writer.setRepository(customerRepository);
    writer.setMethodName("save");
    return writer;
  }

  // @formatter:off
  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("importCustomerJob")
          .incrementer(new RunIdIncrementer())
          .listener(listener)
          .flow(step1())
          .end()
          .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
          .<CustomerDTO, Customer>chunk(10)
          .reader(reader())
          .processor(processor())
          .writer(writer())
          .build();
  }
  // @formatter:on
}
