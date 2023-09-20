package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestDataRepository extends JpaRepository<TestData, UUID> {
}
