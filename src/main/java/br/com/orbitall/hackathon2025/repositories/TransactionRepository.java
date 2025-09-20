package br.com.orbitall.hackathon2025.repositories;

import br.com.orbitall.hackathon2025.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByActiveTrue();
    List<Transaction> findAllByCustomerIdAndActiveTrue(UUID customerId);
}
