package de.rieckpil.batch;

import org.springframework.batch.item.ItemProcessor;
import de.rieckpil.domain.Customer;
import de.rieckpil.dtos.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
  
@Slf4j
public class CustomerItemProcessor implements ItemProcessor<CustomerDTO, Customer>{

  @Override
  public Customer process(CustomerDTO item) throws Exception {

    final String firstName = item.getFirstName().toUpperCase();
    final String lastName = item.getLastName().toUpperCase();
    final Long crmId = item.getCrmId() * 2;
    
    final Customer transformedCustomer = new Customer();
    transformedCustomer.setCrmId(crmId);
    transformedCustomer.setFirstName(firstName);
    transformedCustomer.setLastName(lastName);
    
    log.info(String.format("Convertig: %s to new customer object: %s", item, transformedCustomer));
    return transformedCustomer;
  }

}
