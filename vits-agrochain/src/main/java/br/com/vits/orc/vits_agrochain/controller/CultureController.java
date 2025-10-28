package br.com.vits.orc.vits_agrochain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vits.orc.vits_agrochain.model.Culture;
import br.com.vits.orc.vits_agrochain.service.CultureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cultures")
@Tag(name = "Culturas", description = "Gerenciamento de culturas do sistema Verdantis")
public class CultureController {

    private final CultureService cultureService;

    public CultureController(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar nova cultura", description = "Cria uma nova cultura")
    public Culture createCulture(@RequestBody @Valid Culture culture) {
        log.info("Criando cultura: {}", culture);
        return cultureService.createCulture(culture);
    }

    @GetMapping
    @Operation(summary = "Listar todas as culturas", description = "Retorna lista completa de culturas cadastradas")
    public List<Culture> listAll() {
        log.info("Listando todas as culturas");
        return cultureService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cultura por ID", description = "Retorna uma cultura específica pelo seu ID")
    public Culture getById(@PathVariable Long id) {
        log.info("Buscando cultura com id: {}", id);
        return cultureService.getCultureById(id);
    }
}
