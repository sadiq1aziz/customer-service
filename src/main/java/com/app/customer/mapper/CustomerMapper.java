package com.app.customer.mapper;

import com.app.customer.dto.CustomerDto;
import com.app.customer.dto.MovieDto;
import com.app.customer.entity.Customer;

import java.util.List;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer, List<MovieDto> movies){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getFavoriteGenre(),
                movies);
    }
}
