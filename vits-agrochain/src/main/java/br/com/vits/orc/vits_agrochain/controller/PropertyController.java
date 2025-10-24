package br.com.vits.orc.vits_agrochain.controller;


import br.com.vits.orc.vits_agrochain.model.Property;
import br.com.vits.orc.vits_agrochain.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/properties")
@Tag(name = "Propriedades", description = "Gerenciamento de usuários do sistema Verdantis")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @PostMapping
    @Operation(summary = "Criar nova propriedade", description = "Cria uma nova propriedade")
    public Property createProperty(@RequestBody @Valid Property property, @RequestParam Long userId){
        log.info("Criando propriedade: {} para userId: {}", property, userId);
        return propertyService.createProperty(property, userId);
    }
}
