/**
 * 
 */
package me.koeb.invoiceR.persistence.integration;

/**
 * @author Alexander Köb
 *
 */
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import me.koeb.invoiceR.config.JPAConfiguration;
import me.koeb.invoiceR.persistence.domain.CustomerPO;
import me.koeb.invoiceR.persistence.repository.CustomerRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomerRepositoryIntegrationTests {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {

        CustomerPO customer = new CustomerPO();

        String name = "customer name";
        String contactName = "contact name";
        String addressLine1 = "address line 1";
        String addressLine2 = "address line 2";
        String city = "city";
        String zip = "zip";
        String state = "state";
        String country = "country";
        String email = "email";

        customer.setName(name);
        customer.setContactName(contactName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);
        customer.setCity(city);
        customer.setZip(zip);
        customer.setState(state);
        customer.setCountry(country);
        customer.setEmail(email);

        customerRepository.save(customer);
        int id = customer.getId();
        CustomerPO retrievedCustomer = customerRepository.findById(id);

        assertNotNull(retrievedCustomer);
        assertEquals(1, customerRepository.count());
        assertEquals(retrievedCustomer.getName(), name);
        assertEquals(retrievedCustomer.getContactName(), contactName);
        assertEquals(retrievedCustomer.getAddressLine1(), addressLine1);
        assertEquals(retrievedCustomer.getAddressLine2(), addressLine2);
        assertEquals(retrievedCustomer.getCity(), city);
        assertEquals(retrievedCustomer.getZip(), zip);
        assertEquals(retrievedCustomer.getState(), state);
        assertEquals(retrievedCustomer.getCountry(), country);
        assertEquals(retrievedCustomer.getEmail(), email);

        customerRepository.delete(id);
        assertEquals(0, customerRepository.count());

        System.out.println("customer ID: " + customer.getId() + ", retrievedCustomer: "
                + retrievedCustomer.getId());

    }

}