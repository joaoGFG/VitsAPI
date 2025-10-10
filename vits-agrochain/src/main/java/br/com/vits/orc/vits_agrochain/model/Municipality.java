package br.com.vits.orc.vits_agrochain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Maps VITS_ORC_MUNICIPIO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VITS_ORC_MUNICIPIO")
public class Municipality {

	@Id
	@Column(name = "vits_cod_municipio")
	private Long municipalityCode;

	@NotBlank
	@Size(max = 200)
	@Column(name = "vits_nome_municipio")
	private String municipalityName;

	@ManyToOne()
	@JoinColumn(name = "vits_cod_estado", referencedColumnName = "vits_cod_estado", nullable = false)
	private State state;
}
