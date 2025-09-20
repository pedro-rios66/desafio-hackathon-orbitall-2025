package br.com.orbitall.hackathon2025.repositories;

import br.com.orbitall.hackathon2025.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByActiveTrue();
}
