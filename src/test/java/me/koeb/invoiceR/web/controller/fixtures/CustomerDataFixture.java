package me.koeb.invoiceR.web.controller.fixtures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.koeb.invoiceR.domain.Customer;
import me.koeb.invoiceR.domain.Project;

//TODOCUMENT.  Use of test data fixture classes is considered good practice.
/*
 The majority of tests aren't testing data edge cases, they are testing logical flows and
 what happens to a generic set of data.  For these, use a small, standardised set of fixtures.

 For anything more esoteric, create a new fixture in the test class.
 */
public class CustomerDataFixture {
    public static Customer customerDetails(int id) {
        Customer cust = new Customer();

        cust.setId(id);
        cust.setName("Customer Name #" + id);
        cust.setContactName("Contact Name #" + id);
        cust.setAddressLine1("Address Line 1 #" + id);
        cust.setAddressLine2("Address Line 2 #" + id);
        cust.setCity("City #" + id);
        cust.setZip("Zip #" + id);
        cust.setEmail("Email #" + id);
        cust.setState("State #" + id);
        Set<Project> projects = new HashSet<Project>();
        Project project1 = new Project();
        project1.setProjectName("Project 1 #" + id);
        project1.setHourlyRate(80.5);
        project1.setCustomer(cust);
        projects.add(project1);
        Project project2 = new Project();
        project2.setProjectName("Project 2 #" + id);
        project2.setHourlyRate(90.5);
        project2.setCustomer(cust);
        projects.add(project2);
        return cust;
    }

    public static List<Customer> customerList() {
        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customerDetails(1));
        customerList.add(customerDetails(2));
        customerList.add(customerDetails(3));
        return customerList;
    }
}