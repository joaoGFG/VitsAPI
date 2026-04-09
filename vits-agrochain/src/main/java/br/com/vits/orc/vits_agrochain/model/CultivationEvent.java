package br.com.vits.orc.vits_agrochain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VITS_ORC_EVENTO_CULTIVO")
public class CultivationEvent {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "evento_cultivo_seq_gen"
    )
    @SequenceGenerator(
        name = "evento_cultivo_seq_gen",
        sequenceName = "SEQ_EVENTO_CULTIVO",
        allocationSize = 1
    )
    @Column(name = "vits_id_evento")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vits_id_lote")
    private Lot lot;

    @NotNull
    @Column(name = "vits_data_plantio")
    private LocalDate plantingDate;

    @NotNull
    @Column(name = "vits_data_colheita_est")
    private LocalDate estimatedHarvestDate;

    @NotBlank
    @Column(name = "vits_tipo_evento")
    private String eventType;

    @Column(name = "vits_descricao_evento")
    private String description;

}
