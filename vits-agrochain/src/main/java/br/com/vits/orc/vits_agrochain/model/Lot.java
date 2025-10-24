package br.com.vits.orc.vits_agrochain.model;

import java.time.LocalDate;

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
@Table(name = "VITS_ORC_LOTE")
public class Lot {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "lote_seq_gen"
    )
    @SequenceGenerator(
        name = "lote_seq_gen",
        sequenceName = "SEQ_LOTE",
        allocationSize = 1
    )
    @Column(name = "vits_id_lote")
    private Long lotId;

    @NotNull
    @Column(name = "vits_num_lote")
    private Integer lotNumber;

    @NotNull
    @Column(name = "vits_data_plantio")
    private LocalDate plantingDate;

    @NotNull
    @Column(name = "vits_status_lote")
    private Integer lotStatus; // 0 or 1

    @NotBlank
    @Size(max = 20)
    @Column(name = "vits_area_lote")
    private String lotArea;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vits_id_prop")
    private Property property;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vits_id_cultura")
    private Culture culture;
}
