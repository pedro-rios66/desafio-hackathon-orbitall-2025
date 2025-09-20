package br.com.orbitall.hackathon2025.canonicals;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public class TransactionInput {

    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Card type is required")
    @Pattern(regexp = "DEBIT|CREDIT", message = "Card type must be either DEBIT or CREDIT")
    private String cardType;

    // Getters / Setters
    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
}
