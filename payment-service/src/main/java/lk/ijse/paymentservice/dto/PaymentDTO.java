package lk.ijse.paymentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String paymentId;
    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be positive")
    private double amount;
    @NotNull(message = "Payment Date is mandatory")
    private String paymentDate;
    @NotBlank(message = "Payment Status is mandatory")
    private String paymentStatus;
    @NotBlank(message = "Ticket ID is mandatory")
    private String ticketId;
}
