package br.com.vits.orc.vits_agrochain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VITS_ORC_TIPO_USUARIO")
public class UserType {

    @Id
    @GeneratedValue(                            
        strategy = GenerationType.SEQUENCE,
        generator = "tipo_usuario_seq_gen"
    )
    @SequenceGenerator(
        name = "tipo_usuario_seq_gen", 
        sequenceName = "seq_vits_tipo_usuario", 
        allocationSize = 1
    )
    @Column(name = "id_tipo_usuario")
    private Long userTypeId;

    @Column(name = "usuario_produtor")
    private String producerUser;

    @Column(name = "usuario_gestor")
    private String managerUser;
}