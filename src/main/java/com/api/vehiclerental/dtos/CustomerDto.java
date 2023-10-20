package com.api.vehiclerental.dtos;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private int customerId;
    private String name;
    private String email;
    private String mobile;
    private String licenseNumber;

}
