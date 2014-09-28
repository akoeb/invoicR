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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import me.koeb.invoiceR.config.JPAConfiguration;
import me.koeb.invoiceR.persistence.domain.CustomerPO;
import me.koeb.invoiceR.persistence.domain.ProjectPO;
import me.koeb.invoiceR.persistence.repository.CustomerRepository;
import me.koeb.invoiceR.persistence.repository.ProjectRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectRepositoryIntegrationTests {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory
            .getLogger(ProjectRepositoryIntegrationTests.class);

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {

        String projectName1 = "1: project name";
        double hourlyRate1 = 80.5;

        String projectName2 = "2: project name";
        double hourlyRate2 = 90.5;

        String customerName = "customer name";
        // HashSet<Project> projectSet = new HashSet<Project>();

        CustomerPO customer = new CustomerPO();
        customer.setName(customerName);
        customerRepository.save(customer);
        int customerID = customer.getId();
        assertTrue(customerID > 0);

        // just to make sure the customer persistence works as well:
        CustomerPO retrievedCustomer = customerRepository.findById(customerID);
        assertNotNull(retrievedCustomer);
        assertEquals(1, customerRepository.count());

        // *********************************** //
        // Project 1

        logger.info("\nSTARTING WITH PROJECT 1\n");

        ProjectPO project1 = new ProjectPO();
        project1.setName(projectName1);
        project1.setHourlyRate(hourlyRate1);
        customer.addProject(project1);

        projectRepository.save(project1);
        // customerRepository.save(customer);

        logger.info("\nPROJECT 1  AFTER SAVING\n");

        int projectId1 = project1.getId();
        assertTrue(projectId1 > 0);
        assertEquals(1, projectRepository.count());
        assertEquals(1, customerRepository.count());

        ProjectPO retrievedProject1 = projectRepository.findById(projectId1);
        assertNotNull(retrievedProject1);

        assertEquals(retrievedProject1.getName(), projectName1);
        assertEquals(retrievedProject1.getHourlyRate(), hourlyRate1, 0.0);
        assertEquals(customerName, retrievedProject1.getCustomer().getName());

        retrievedCustomer = customerRepository.findById(customerID);

        Set<ProjectPO> projectList = retrievedCustomer.getProjects();
        assertNotNull(projectList);
        assertEquals(1, projectList.size());
        assertTrue(projectList.contains(project1));

        // *********************************** //
        // Project 2

        ProjectPO project2 = new ProjectPO();
        project2.setName(projectName2);
        project2.setHourlyRate(hourlyRate2);
        customer.addProject(project2);

        projectRepository.save(project2);

        int projectId2 = project2.getId();
        assertTrue(projectId2 > 0);
        assertEquals(2, projectRepository.count());
        assertEquals(1, customerRepository.count());

        ProjectPO retrievedProject2 = projectRepository.findById(projectId2);

        assertNotNull(retrievedProject2);
        assertEquals(projectName2, retrievedProject2.getName());
        assertEquals(hourlyRate2, retrievedProject2.getHourlyRate(), 0.0);
        assertEquals(customerName, retrievedProject2.getCustomer().getName());

        // overwriting the variable from earlier tests:
        projectList = retrievedProject2.getCustomer().getProjects();
        assertNotNull(projectList);
        assertEquals(2, projectList.size());
        assertTrue(projectList.contains(project1));
        assertTrue(projectList.contains(project2));

        // *********************************** //
        // Customer again

        // overwriting variable from earlier:
        retrievedCustomer = customerRepository.findById(customerID);
        assertNotNull(retrievedCustomer);

        // overwriting variable in use earlier
        projectList = retrievedCustomer.getProjects();
        assertNotNull(projectList);
        assertEquals(projectList.size(), 2);
        assertTrue(projectList.contains(project1));
        assertTrue(projectList.contains(project2));

        // *********************************** //
        // deleting project 1:

        customer.deleteProject(project1);
        projectRepository.delete(project1);

        assertEquals(1, projectRepository.count());
        assertEquals(1, customerRepository.count());
        assertEquals(1, retrievedCustomer.getProjects().size());
        assertFalse(retrievedCustomer.getProjects().contains(project1));
        assertTrue(retrievedCustomer.getProjects().contains(project2));

        // overwriting variable from earlier:
        retrievedProject1 = projectRepository.findById(projectId1);
        // cannot have anything returned here:
        assertNull(retrievedProject1);

        // overwriting variable from earlier:
        retrievedCustomer = customerRepository.findById(customerID);
        assertNotNull(retrievedCustomer);

        // overwriting variable in use earlier
        projectList = retrievedCustomer.getProjects();
        assertNotNull(projectList);
        assertEquals(1, projectList.size());
        assertTrue(projectList.contains(project2));

        // *********************************** //
        // deleting project 2:

        projectRepository.delete(project2);
        customer.deleteProject(project2);

        assertEquals(0, projectRepository.count());
        assertEquals(1, customerRepository.count());

        // overwriting variable from earlier:
        retrievedProject2 = projectRepository.findById(projectId2);
        // cannot have anything returned here:
        assertNull(retrievedProject2);

        // overwriting variable from earlier:
        retrievedCustomer = customerRepository.findById(customerID);
        assertNotNull(retrievedCustomer);

        // overwriting variable in use earlier
        projectList = retrievedCustomer.getProjects();
        assertNotNull(projectList);
        assertEquals(projectList.size(), 0);

    }
}