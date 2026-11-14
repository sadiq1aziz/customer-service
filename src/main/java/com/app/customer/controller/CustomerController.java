package com.app.customer.controller;

import com.app.customer.domain.Genre;
import com.app.customer.dto.CustomerDto;
import com.app.customer.dto.UpdateGenreRequest;
import com.app.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

        private final CustomerService service;

        public CustomerController(CustomerService service){
            this.service = service;
        }

        @GetMapping({"/{id}", "/customer/{id}"})
        public ResponseEntity<CustomerDto> fetchCustomersById(@PathVariable Integer id){
            var dto = service.getCustomer(id);
            return ResponseEntity.ok().body(dto);
        }

        @GetMapping("/{name}")
        public ResponseEntity<CustomerDto> fetchCustomerByName(@PathVariable String name){
            var dto = service.getCustomerByName(name);
            return ResponseEntity.ok().body(dto);
        }

        @PatchMapping({"/{id}/genre", "/customer/{id}/genre"})
        public ResponseEntity<Void> updateMovieByGenre(@PathVariable Integer id, @RequestBody UpdateGenreRequest req){
            service.updateCustomerGenre(id, req);
            return ResponseEntity.noContent().build();
        }
    }

