package com.example.kiosk7;

public enum CustomerType {
    SOLDIER("군인"),
    STUDENT("학생"),
    ORDINARY("일반인"),
    CREW("직원");

    private String customerType;

    private CustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }
}
