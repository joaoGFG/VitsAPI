package br.com.vits.orc.vits_agrochain.controller;

import br.com.vits.orc.vits_agrochain.dto.LotRequest;
import br.com.vits.orc.vits_agrochain.dto.LotResponse;
import br.com.vits.orc.vits_agrochain.model.Culture;
import br.com.vits.orc.vits_agrochain.model.Lot;
import br.com.vits.orc.vits_agrochain.model.Property;
import br.com.vits.orc.vits_agrochain.repository.CultureRepository;
import br.com.vits.orc.vits_agrochain.repository.PropertyRepository;
import br.com.vits.orc.vits_agrochain.service.LotService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lotes")
public class LotController {

    private final LotService lotService;
    private final CultureRepository cultureRepository;
    private final PropertyRepository propertyRepository;

    public LotController(LotService lotService, CultureRepository cultureRepository, PropertyRepository propertyRepository) {
        this.lotService = lotService;
        this.cultureRepository = cultureRepository;
        this.propertyRepository = propertyRepository;
    }

    private LotResponse convertToResponse(Lot lot) {
        Double totalProduction = lot.getTotalProduction() != null ? lot.getTotalProduction() : 0.0;
        Double salePrice = lot.getSalePrice() != null ? lot.getSalePrice() : 0.0;
        Double totalCost = lot.getTotalCost() != null ? lot.getTotalCost() : 0.0;
        Double estimatedRevenue = totalProduction * salePrice;
        Double estimatedProfit = estimatedRevenue - totalCost;
        
        return new LotResponse(
                lot.getLotId(),
                "Lote " + lot.getLotNumber(), 
                lot.getCulture() != null ? lot.getCulture().getCultureName() : "N/A",
                totalProduction,
                totalCost,
                estimatedRevenue,
                estimatedProfit,
                lot.getLotStatus() != null && lot.getLotStatus() == 1 ? "finalizado" : "ativo"
        );
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public void createLot(@RequestBody @Valid LotRequest request) {
        Culture culture = cultureRepository.findAll().stream().findFirst().orElse(null);
        Property property = propertyRepository.findAll().stream().findFirst().orElse(null);

        Lot lot = Lot.builder()
                .lotNumber((int)(Math.random() * 1000))
                .plantingDate(LocalDate.now())
                .lotStatus(0) // 0 = ativo
                .lotArea("100ha")
                .totalProduction(request.totalProduction())
                .totalCost(request.totalCost())
                .salePrice(request.salePrice())
                .culture(culture)
                .property(property)
                .build();
        
        lotService.createLot(lot);
    }

    @GetMapping
    public List<LotResponse> getAll() {
        return lotService.listAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<LotResponse> getById(@PathVariable Long id) {
        Lot lot = lotService.findById(id);
        return List.of(convertToResponse(lot));
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteLot(@PathVariable Long id) {
        lotService.deleteLot(id);
        return Map.of("mensagem", "Lote removido com sucesso");
    }
}
