package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private String vehicleId;
    @NotBlank(message = "Vehicle License plate cannot be blank")
    private String licensePlate;
    @NotBlank(message = "Vehicle Brand cannot be blank")
    private String brand;
    @NotBlank(message = "Vehicle Model cannot be blank")
    private String model;
    @NotBlank(message = "Vehicle User ID cannot be blank")
    private String userId;
}
