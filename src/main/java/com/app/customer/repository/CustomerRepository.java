package com.app.customer.repository;

import com.app.customer.dto.CustomerDto;
import com.app.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findCustomerByName(String name);
}
