package br.com.orbitall.hackathon2025.services;

import br.com.orbitall.hackathon2025.canonicals.CustomerInput;
import br.com.orbitall.hackathon2025.canonicals.CustomerOutput;
import br.com.orbitall.hackathon2025.exceptions.ResourceNotFoundException;
import br.com.orbitall.hackathon2025.models.Customer;
import br.com.orbitall.hackathon2025.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerOutput> getAllActiveCustomers() {
        return customerRepository.findAllByActiveTrue().stream()
                .map(c -> new CustomerOutput(
                        c.getId(),
                        c.getFullName(),
                        c.getEmail(),
                        c.getPhone(),
                        c.getCreatedAt(),
                        c.getUpdatedAt(),
                        c.isActive()
                ))
                .collect(Collectors.toList());
    }
    
    public List<CustomerOutput> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(c -> new CustomerOutput(
                        c.getId(),
                        c.getFullName(),
                        c.getEmail(),
                        c.getPhone(),
                        c.getCreatedAt(),
                        c.getUpdatedAt(),
                        c.isActive()
                ))
                .collect(Collectors.toList());
    }

    public CustomerOutput getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        return new CustomerOutput(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                customer.isActive()
        );
    }

    @Transactional
    public CustomerOutput createCustomer(CustomerInput input) {
        Customer customer = new Customer();
        customer.setFullName(input.getFullName());
        customer.setEmail(input.getEmail());
        customer.setPhone(input.getPhone());
        customer.setActive(true);

        Customer saved = customerRepository.save(customer);

        return new CustomerOutput(
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getCreatedAt(),
                saved.getUpdatedAt(),
                saved.isActive()
        );
    }

    @Transactional
    public CustomerOutput updateCustomer(UUID id, CustomerInput input) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        customer.setFullName(input.getFullName());
        customer.setEmail(input.getEmail());
        customer.setPhone(input.getPhone());

        Customer updated = customerRepository.save(customer);

        return new CustomerOutput(
                updated.getId(),
                updated.getFullName(),
                updated.getEmail(),
                updated.getPhone(),
                updated.getCreatedAt(),
                updated.getUpdatedAt(),
                updated.isActive()
        );
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        customer.setActive(false);
        customerRepository.save(customer);
    }
}
