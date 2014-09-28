/**
 * 
 */
package me.koeb.invoiceR.web.controller;

import static me.koeb.invoiceR.web.controller.fixtures.CustomerDataFixture.customerDetails;
import static me.koeb.invoiceR.web.controller.fixtures.CustomerDataFixture.customerList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import me.koeb.invoiceR.config.JPAConfiguration;
import me.koeb.invoiceR.domain.Customer;
import me.koeb.invoiceR.persistence.domain.CustomerPO;
import me.koeb.invoiceR.persistence.repository.CustomerRepository;
import me.koeb.invoiceR.service.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Alexander Köb
 *
 */
@ContextConfiguration(classes = { JPAConfiguration.class })
public class CustomerControllerIntegrationTest {
    MockMvc mockMvc;

    @InjectMocks
    CustomerController controller;

    @Mock
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatViewOrderUsesHttpNotFound() throws Exception {

        // the result of the service call should be null:
        // when(customerService.getCustomerDetails(any(Integer.class))).thenReturn(null);

        // this fires a get request to customer id 1,
        // since this is not set it expects a not found status
        this.mockMvc.perform(
                get("/customer/3432")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void thatViewOrderRendersCorrectly() throws Exception {
        // this service call should return something
        when(customerService.getCustomerDetails(any(Integer.class))).thenReturn(
                customerDetails(3432));

        this.mockMvc.perform(
                get("/customer/3432")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items['no']").value(12))
                .andExpect(jsonPath("$.key").value("hm"));
    }

    @Test
    public void thatListRendersCorrectly() throws Exception {
        // this service call should return something
        when(customerService.getAllCustomers()).thenReturn(
                customerList());

        this.mockMvc.perform(
                get("/customer/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items['no']").value(12))
                .andExpect(jsonPath("$.key").value("hm"));
    }

    @Test
    public void thatInsertWorksCorrectly() throws Exception {
        performInsert();
    }

    @Test
    public void thatUpdateWorksCorrectly() throws Exception {

        int id = performInsert();

        Customer updatedCustomer = customerDetails(id);

        ObjectMapper mapper = new ObjectMapper();
        String customerJSON = mapper.writeValueAsString(updatedCustomer);

        // and perform the update:
        this.mockMvc.perform(
                put("/customer/" + id).content(customerJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Location", Matchers.endsWith("/customer/" + id)))
                .andReturn();

        CustomerPO retrievedCustomer = customerRepository.findById(id);
        assertEquals(updatedCustomer.getName(), retrievedCustomer.getName());
        assertEquals(updatedCustomer.getContactName(), retrievedCustomer.getContactName());
        assertEquals(updatedCustomer.getAddressLine1(), retrievedCustomer.getAddressLine1());
        assertEquals(updatedCustomer.getAddressLine2(), retrievedCustomer.getAddressLine2());
        assertEquals(updatedCustomer.getCity(), retrievedCustomer.getCity());
        assertEquals(updatedCustomer.getZip(), retrievedCustomer.getZip());
        assertEquals(updatedCustomer.getEmail(), retrievedCustomer.getEmail());
        assertEquals(updatedCustomer.getState(), retrievedCustomer.getState());
    }

    @Test
    public void thatDeleteWorksCorrectly() throws Exception {

        int insertedId = performInsert();

        // and check the database content:
        CustomerPO retrievedCustomer = customerRepository.findById(insertedId);
        assertNotNull(retrievedCustomer);

        this.mockMvc.perform(
                delete("/customer/" + insertedId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items['no']").value(12))
                .andExpect(jsonPath("$.key").value("hm"));

        CustomerPO retrievedCustomer2 = customerRepository.findById(insertedId);
        assertNotNull(retrievedCustomer2);

    }

    private int performInsert() throws Exception {

        Customer insertedCustomer = customerDetails(0);

        // the object as json string:
        ObjectMapper mapper = new ObjectMapper();
        String customerJSON = mapper.writeValueAsString(insertedCustomer);

        // this should test all the way, so we do not mock the service call
        // this service call should return a customer
        // when(customerService.createCustomer(any(CustomerTO.class)))
        // .thenReturn(insertedCustomer);

        // the response of creation is correct
        MvcResult result = this.mockMvc.perform(
                post("/customer/").content(customerJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.contains("/customer/")))
                .andReturn();

        // extract header
        String redirect = result.getResponse().getHeader("Location");

        // we check the redirect:
        this.mockMvc.perform(
                get(redirect)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items['no']").value(12))
                .andExpect(jsonPath("$.key").value("hm"));

        // and we check the database:
        int id = Integer.parseInt(redirect);
        assertTrue(id > 0);

        // and check the database content:
        CustomerPO retrievedCustomer = customerRepository.findById(id);
        assertEquals(insertedCustomer.getName(), retrievedCustomer.getName());
        assertEquals(insertedCustomer.getContactName(), retrievedCustomer.getContactName());
        assertEquals(insertedCustomer.getAddressLine1(), retrievedCustomer.getAddressLine1());
        assertEquals(insertedCustomer.getAddressLine2(), retrievedCustomer.getAddressLine2());
        assertEquals(insertedCustomer.getCity(), retrievedCustomer.getCity());
        assertEquals(insertedCustomer.getZip(), retrievedCustomer.getZip());
        assertEquals(insertedCustomer.getEmail(), retrievedCustomer.getEmail());
        assertEquals(insertedCustomer.getState(), retrievedCustomer.getState());
        return id;
    }
}
