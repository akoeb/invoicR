/**
 * Testing existence of the customer table in the db backend
 */
package me.koeb.invoiceR.persistence.integration;

/**
 * @author Alexander Köb
 *
 */

import static me.koeb.invoiceR.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static me.koeb.invoiceR.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

import javax.persistence.EntityManager;

import me.koeb.invoiceR.config.JPAConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomerIntegrationTest {

    @Autowired
    @Qualifier(value = "entityManager")
    EntityManager manager;

    @Test
    public void thatCustomerMappingWorks() throws Exception {
        assertTableExists(manager, "customers");

        assertTableHasColumn(manager, "customers", "name");
        assertTableHasColumn(manager, "customers", "id");
        assertTableHasColumn(manager, "customers", "addressLine1");
        assertTableHasColumn(manager, "customers", "addressLine2");
        assertTableHasColumn(manager, "customers", "city");
        assertTableHasColumn(manager, "customers", "zip");
        assertTableHasColumn(manager, "customers", "country");
        assertTableHasColumn(manager, "customers", "state");
        assertTableHasColumn(manager, "customers", "email");
        assertTableHasColumn(manager, "customers", "contactName");

    }

}
