package br.com.orbitall.hackathon2025.services;

import br.com.orbitall.hackathon2025.canonicals.TransactionInput;
import br.com.orbitall.hackathon2025.canonicals.TransactionOutput;
import br.com.orbitall.hackathon2025.exceptions.ResourceNotFoundException;
import br.com.orbitall.hackathon2025.models.Customer;
import br.com.orbitall.hackathon2025.models.Transaction;
import br.com.orbitall.hackathon2025.repositories.CustomerRepository;
import br.com.orbitall.hackathon2025.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public List<TransactionOutput> getAllTransactions() {
        return transactionRepository.findAllByActiveTrue().stream()
                .map(t -> new TransactionOutput(
                        t.getId(),
                        t.getCustomerId(),
                        t.getAmount(),
                        t.getCardType(),
                        t.getCreatedAt(),
                        t.getUpdatedAt(),
                        t.isActive()
                ))
                .collect(Collectors.toList());
    }
    
    public List<TransactionOutput> getTransactionsByCustomerId(UUID customerId) {
        // Verificar se o cliente existe
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + customerId));
                
        return transactionRepository.findAllByCustomerIdAndActiveTrue(customerId).stream()
                .map(t -> new TransactionOutput(
                        t.getId(),
                        t.getCustomerId(),
                        t.getAmount(),
                        t.getCardType(),
                        t.getCreatedAt(),
                        t.getUpdatedAt(),
                        t.isActive()
                ))
                .collect(Collectors.toList());
    }

    public TransactionOutput getTransactionById(UUID id) {
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));

        return new TransactionOutput(
                t.getId(),
                t.getCustomerId(),
                t.getAmount(),
                t.getCardType(),
                t.getCreatedAt(),
                t.getUpdatedAt(),
                t.isActive()
        );
    }

    @Transactional
    public TransactionOutput createTransaction(TransactionInput input) {
        Customer customer = customerRepository.findById(input.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + input.getCustomerId()));

        Transaction t = new Transaction();
        t.setCustomerId(customer.getId());
        t.setAmount(input.getAmount());
        t.setCardType(input.getCardType());
        t.setActive(true);

        Transaction saved = transactionRepository.save(t);

        return new TransactionOutput(
                saved.getId(),
                saved.getCustomerId(),
                saved.getAmount(),
                saved.getCardType(),
                saved.getCreatedAt(),
                saved.getUpdatedAt(),
                saved.isActive()
        );
    }

    @Transactional
    public TransactionOutput updateTransaction(UUID id, TransactionInput input) {
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));

        // Só atualizar amount e cardType, manter createdAt
        t.setAmount(input.getAmount());
        t.setCardType(input.getCardType());

        Transaction updated = transactionRepository.save(t);

        return new TransactionOutput(
                updated.getId(),
                updated.getCustomerId(),
                updated.getAmount(),
                updated.getCardType(),
                updated.getCreatedAt(),
                updated.getUpdatedAt(),
                updated.isActive()
        );
    }

    @Transactional
    public void deleteTransaction(UUID id) {
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        t.setActive(false);
        transactionRepository.save(t);
    }
}
