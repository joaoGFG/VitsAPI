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
        sequenceName = "SEQ_TIPO_USUARIO", 
        allocationSize = 1
    )
    @Column(name = "id_tipo_usuario")
    private Long userTypeId;

    @Column(name = "vits_usuario_descrição", length = 50)
    private String userDescription;
}