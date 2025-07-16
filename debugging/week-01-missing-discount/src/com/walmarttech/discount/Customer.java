package com.walmarttech.discount;

public class Customer {
    private String name;
    // private String membershipLevel;
    private MembershipLevel membershipLevel;

    public Customer(String name, MembershipLevel membershipLevel) {
        this.name = name;
        this.membershipLevel = membershipLevel;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public String getName() {
        return name;
    }
}
