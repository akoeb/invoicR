/**
 * 
 */
package me.koeb.invoiceR.persistence.repository;

/**
 * @author Alexander Köb
 *
 */

import me.koeb.invoiceR.persistence.domain.Project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    Project findById(int Id);
}
