package br.com.vits.orc.vits_agrochain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 200)
    String userName,
    
    @NotBlank(message = "Email é obrigatório")
    @Email
    @Size(max = 200)
    String email,
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100)
    String password
) {}
