/**
 * 
 */
package me.koeb.invoiceR.domain;

/**
 * @author Alexander Köb
 *
 */
public class Project {
    private int id;
    private Customer customer;
    private String projectName;
    private double hourlyRate;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName
     *            the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the hourlyRate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * @param d
     *            the hourlyRate to set
     */
    public void setHourlyRate(double d) {
        this.hourlyRate = d;
    }

}
