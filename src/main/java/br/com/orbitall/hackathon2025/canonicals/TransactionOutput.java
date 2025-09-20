package br.com.orbitall.hackathon2025.canonicals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionOutput {

    private UUID id;
    private UUID customerId;
    private BigDecimal amount;
    private String cardType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    public TransactionOutput(UUID id, UUID customerId, BigDecimal amount, String cardType,
                             LocalDateTime createdAt, LocalDateTime updatedAt, boolean active) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.cardType = cardType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }
    public String getCardType() { return cardType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isActive() { return active; }
}
