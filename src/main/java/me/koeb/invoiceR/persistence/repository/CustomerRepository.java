/**
 * 
 */
package me.koeb.invoiceR.persistence.repository;

/**
 * @author Alexander Köb
 *
 */

import me.koeb.invoiceR.persistence.domain.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findById(int Id);
}
