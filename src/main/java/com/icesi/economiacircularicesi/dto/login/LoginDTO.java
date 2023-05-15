package com.icesi.economiacircularicesi.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginDTO {
    private String email;
    private String password;
}
