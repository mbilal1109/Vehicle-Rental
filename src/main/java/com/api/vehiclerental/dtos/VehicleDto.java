package com.api.vehiclerental.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    private int vehicleId;
    private String make;
    private String model;
    private String trim;
    private String year;
}
