package com.discount;

public class Customer {
    private String name;
    private String membershipLevel;

    public Customer(String name, String membershipLevel) {
        this.name = name;
        this.membershipLevel = membershipLevel;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getName() {
        return name;
    }
}
