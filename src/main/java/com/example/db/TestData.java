package com.example.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "test_data")
@EntityListeners(TenantListener.class)
public class TestData implements TenantAware{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String value;

    private String tenant;

    protected TestData(){}

    public TestData(String value){
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
