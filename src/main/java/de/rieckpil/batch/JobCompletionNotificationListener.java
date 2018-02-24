package de.rieckpil.batch;

import java.util.List;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import de.rieckpil.domain.Customer;
import de.rieckpil.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final CustomerRepository customerRepository;

    public JobCompletionNotificationListener(CustomerRepository customerRepository) {
      this.customerRepository = customerRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<Customer> customers = customerRepository.findAll();
            
            for (Customer customer : customers) {
              log.info(String.format("Found customer <%s>", customer));
            }
        }
    }
}