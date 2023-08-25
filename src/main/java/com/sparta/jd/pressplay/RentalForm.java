package com.sparta.jd.pressplay;

public class RentalForm {
    private Integer daysToBeRented;
    private Integer staffId;
    private Integer customerId;
    private String customerLastName;

    public Integer getDaysToBeRented() {
        return daysToBeRented;
    }

    public void setDaysToBeRented(Integer daysToBeRented) {
        this.daysToBeRented = daysToBeRented;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public RentalForm() {
    }
}
