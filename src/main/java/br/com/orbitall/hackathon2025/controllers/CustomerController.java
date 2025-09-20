package br.com.orbitall.hackathon2025.controllers;

import br.com.orbitall.hackathon2025.canonicals.CustomerInput;
import br.com.orbitall.hackathon2025.canonicals.CustomerOutput;
import br.com.orbitall.hackathon2025.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerOutput> getAllCustomers() {
        return customerService.getAllActiveCustomers();
    }

    @GetMapping("/{id}")
    public CustomerOutput getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerOutput> createCustomer(@Valid @RequestBody CustomerInput input) {
        CustomerOutput created = customerService.createCustomer(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public CustomerOutput updateCustomer(@PathVariable UUID id, @Valid @RequestBody CustomerInput input) {
        return customerService.updateCustomer(id, input);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
