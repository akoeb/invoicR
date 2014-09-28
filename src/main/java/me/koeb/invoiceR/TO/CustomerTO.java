/**
 * 
 */
package me.koeb.invoiceR.TO;

import java.util.HashSet;
import java.util.Set;

import me.koeb.invoiceR.persistence.domain.CustomerPO;

/**
 * @author Alexander Köb
 *
 */
public class CustomerTO {
    private int id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zip;
    private String country;
    private String state;
    private String email;
    private String contactName;
    private Set<ProjectTO> projects = new HashSet<ProjectTO>();

    // two constructors, one empty and one for constructing the
    // transfer object from an persistence object
    public CustomerTO() {
    }

    public CustomerTO(CustomerPO customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.addressLine1 = customer.getAddressLine1();
        this.addressLine2 = customer.getAddressLine2();
        this.city = customer.getCity();
        this.zip = customer.getZip();
        this.country = customer.getCountry();
        this.state = customer.getState();
        this.email = customer.getEmail();
        this.contactName = customer.getContactName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1
     *            the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2
     *            the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     *            the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     *            the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the projects
     */
    public Set<ProjectTO> getProjects() {
        return projects;
    }

    /**
     * @param projects
     *            the projects to set
     */
    public void setProjects(Set<ProjectTO> projects) {
        this.projects = projects;
    }

}
