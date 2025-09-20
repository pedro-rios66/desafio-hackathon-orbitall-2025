package br.com.orbitall.hackathon2025.controllers;

import br.com.orbitall.hackathon2025.canonicals.TransactionInput;
import br.com.orbitall.hackathon2025.canonicals.TransactionOutput;
import br.com.orbitall.hackathon2025.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionOutput> getAllTransactions(@RequestParam(required = false) UUID customerId) {
        if (customerId != null) {
            return transactionService.getTransactionsByCustomerId(customerId);
        }
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionOutput getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public ResponseEntity<TransactionOutput> createTransaction(@Valid @RequestBody TransactionInput input) {
        TransactionOutput created = transactionService.createTransaction(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public TransactionOutput updateTransaction(@PathVariable UUID id, @Valid @RequestBody TransactionInput input) {
        return transactionService.updateTransaction(id, input);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
