/**
 * 
 */
package me.koeb.invoiceR.service;

import me.koeb.invoiceR.domain.Customer;
import me.koeb.invoiceR.events.RequestCreateCustomerEvent;

/**
 * @author Alexander Köb
 *
 */
public class CustomerService {
    public Customer createCustomer(RequestCreateCustomerEvent event) {
        // CustomerDAO dao = new CustomerDAO();
        // Customer customer = dao.createCustomer(event);
        return new Customer();
    }
    // updateCustomer
    // deleteCustomer
    // getCustomer
    // getAllCustomers

}
