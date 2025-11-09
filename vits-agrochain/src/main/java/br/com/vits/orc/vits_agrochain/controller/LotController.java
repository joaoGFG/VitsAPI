package br.com.vits.orc.vits_agrochain.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vits.orc.vits_agrochain.model.Lot;
import br.com.vits.orc.vits_agrochain.service.LotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/lots")
@Tag(name = "Lotes", description = "Gerenciamento de lotes do sistema Verdantis")
public class LotController {

	private final LotService lotService;

	public LotController(LotService lotService) {
		this.lotService = lotService;
	}

	@GetMapping
	@Operation(summary = "Listar todos os lotes com paginação", description = "Retorna lista paginada de lotes com links HATEOAS")
	public PagedModel<EntityModel<Lot>> getAll(
			@PageableDefault(size = 10, sort = "lotNumber") Pageable pageable,
			PagedResourcesAssembler<Lot> assembler) {
		log.info("Listando lotes paginados - página: {}, tamanho: {}", pageable.getPageNumber(), pageable.getPageSize());
		var page = lotService.listAllPaginated(pageable);
		return assembler.toModel(page, Lot::toEntityModel);
	}

	@GetMapping("/all")
	@Operation(summary = "Listar todos os lotes sem paginação", description = "Retorna lista completa de lotes")
	public List<Lot> listAll() {
		log.info("Listando todos os lotes");
		return lotService.listAll();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar lote por ID", description = "Retorna um lote específico com links HATEOAS")
	public EntityModel<Lot> getById(@PathVariable Long id) {
		log.info("Buscando lote com id: {}", id);
		var lot = lotService.getLotById(id);
		return lot.toEntityModel();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Criar novo lote", description = "Cria um novo lote")
	public Lot createLot(@RequestBody @Valid Lot lot) {
		log.info("Criando lote: {}", lot);
		return lotService.createLot(lot);
	}

}
