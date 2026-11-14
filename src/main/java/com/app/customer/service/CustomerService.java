package com.app.customer.service;

import com.app.customer.client.MovieRestClient;
import com.app.customer.domain.Genre;
import com.app.customer.dto.CustomerDto;
import com.app.customer.dto.MovieDto;
import com.app.customer.dto.UpdateGenreRequest;
import com.app.customer.entity.Customer;
import com.app.customer.exceptions.CustomerNotFoundException;
import com.app.customer.mapper.CustomerMapper;
import com.app.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final MovieRestClient client;
    private final CustomerRepository repo;
    public CustomerService(MovieRestClient client, CustomerRepository customerRepo){
        this.client = client;
        this.repo = customerRepo;
    }

    public CustomerDto getCustomerByName(String name){
       Optional<Customer> op = repo.findCustomerByName(name);
       if(!op.isPresent()){
           throw new CustomerNotFoundException(name);
       }
       Customer customer = op.get();
       log.info("customer genre: {}", customer.getFavoriteGenre());
       List<MovieDto> movieRecommendationsList = client.callMoviesByGenre(customer.getFavoriteGenre());
       return CustomerMapper.toCustomerDto(customer, movieRecommendationsList);
    }

    public CustomerDto getCustomer(Integer id){
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        List<MovieDto> list = client.callMoviesByGenre(customer.getFavoriteGenre());
        return CustomerMapper. toCustomerDto(customer, list);
    }

    public void updateCustomerGenre(Integer id, UpdateGenreRequest req){
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setFavoriteGenre(req.favoriteGenre());
        repo.save(customer);
    }
}
