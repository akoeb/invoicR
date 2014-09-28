/**
 * 
 */
package me.koeb.invoiceR.persistence.repository;

/**
 * @author Alexander Köb
 *
 */

import me.koeb.invoiceR.persistence.domain.ProjectPO;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectPO, Integer> {
    ProjectPO findById(int Id);
}
