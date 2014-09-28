/**
 * 
 */
package me.koeb.invoiceR.persistence.repository;

/**
 * @author Alexander Köb
 *
 */

import me.koeb.invoiceR.persistence.domain.CustomerPO;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerPO, Integer> {
    CustomerPO findById(int Id);
}
