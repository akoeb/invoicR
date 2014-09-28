/**
 * 
 */
package me.koeb.invoiceR.service;

import java.util.ArrayList;
import java.util.List;

import me.koeb.invoiceR.TO.CustomerTO;
import me.koeb.invoiceR.domain.Customer;
import me.koeb.invoiceR.persistence.domain.CustomerPO;
import me.koeb.invoiceR.persistence.repository.CustomerRepository;
import me.koeb.invoiceR.web.controller.SiteController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alexander Köb
 *
 */
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    public Customer getCustomerDetails(int customerId) {
        logger.info("Service getCustomerDetails called for id {}", customerId);
        CustomerTO customerTO = new CustomerTO(customerRepository.findById(customerId));
        // if we did not find anything, then the transfer object is initialized
        // with default
        // for int this is 0:
        if (customerTO.getId() == 0) {
            return null;
        }
        return new Customer(customerTO);
    }

    public Customer createCustomer(CustomerTO customerTO) {
        CustomerPO customerPO = new CustomerPO(customerTO);
        customerRepository.save(customerPO);
        customerTO.setId(customerPO.getId());
        logger.info("Customer created with id {}", customerTO.getId());
        return new Customer(customerTO);
    }

    public Customer updateCustomer(int customerId, CustomerTO customerTO) {
        CustomerPO customerPO = customerRepository.findById(customerId);
        if (customerPO == null) {
            return null;
        }
        customerPO.setName(customerTO.getName());
        customerPO.setContactName(customerTO.getContactName());
        customerPO.setAddressLine1(customerTO.getAddressLine1());
        customerPO.setAddressLine2(customerTO.getAddressLine2());
        customerPO.setCity(customerTO.getCity());
        customerPO.setZip(customerTO.getZip());
        customerPO.setCountry(customerTO.getCountry());
        customerPO.setState(customerTO.getState());
        customerPO.setEmail(customerTO.getEmail());
        customerRepository.save(customerPO);
        return new Customer(customerTO);
    }

    public List<Customer> getAllCustomers() {
        Iterable<CustomerPO> customerIterator = customerRepository.findAll();
        List<Customer> retval = new ArrayList<Customer>();
        while (customerIterator.iterator().hasNext()) {
            CustomerPO customerPO = customerIterator.iterator().next();
            CustomerTO customerTO = new CustomerTO(customerPO);
            retval.add(new Customer(customerTO));
        }
        return retval;
    }

    public boolean deleteCustomer(int customerId) {
        CustomerPO customer = customerRepository.findById(customerId);
        if (customer == null) {
            return false;
        }
        customerRepository.delete(customer);
        return true;
    }

}
