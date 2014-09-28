/**
 * 
 */
package me.koeb.invoiceR.web.controller;

import java.util.ArrayList;
import java.util.List;

import me.koeb.invoiceR.TO.CustomerTO;
import me.koeb.invoiceR.domain.Customer;
import me.koeb.invoiceR.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Alexander Köb
 *
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ******************* mappings *********************//

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        LOG.info("List of all Customers");
        return new ArrayList<Customer>();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable("customerId") int id) {
        LOG.info("Customer details for customer #{}", id);
        Customer customer = customerService.getCustomerDetails(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> insertCustomer(
            @RequestBody CustomerTO customerTO,
            UriComponentsBuilder builder)
    {
        Customer customer = customerService.createCustomer(customerTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customer/{id}")
                .buildAndExpand(customer.getId()).toUri());

        return new ResponseEntity<Customer>(customer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("customerId") int id,
            @RequestBody CustomerTO customerTO,
            UriComponentsBuilder builder)
    {

        Customer customer = customerService.updateCustomer(id, customerTO);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customer/{id}")
                .buildAndExpand(customer.getId()).toUri());

        return new ResponseEntity<Customer>(customer, headers, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {

        boolean deleted = customerService.deleteCustomer(id);

        if (!deleted) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);

        // return new ResponseEntity<Order>(order, HttpStatus.FORBIDDEN);
    }
}
