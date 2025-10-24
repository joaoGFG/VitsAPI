package br.com.vits.orc.vits_agrochain.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VITS_ORC_USUARIO")
public class User {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,  
        generator = "usuario_seq_gen"        
    )
    @SequenceGenerator(
        name = "usuario_seq_gen", 
        sequenceName = "SEQ_USUARIO",     
        allocationSize = 1
    )
    @Column(name = "vits_id_usuario")
    private Long userId;

    @NotBlank
    @Size(max = 200)
    @Column(name = "vits_nome_usuario")
    private String userName;

    @NotNull
    @Column(name = "vits_data_cadastro")
    private LocalDateTime registrationDate;    
    
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_usuario") 
    private UserType userType;
}