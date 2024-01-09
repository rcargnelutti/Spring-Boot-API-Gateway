package com.example.springboot.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceModel, UUID> {
}
