package br.com.vits.orc.vits_agrochain.service;

import br.com.vits.orc.vits_agrochain.dto.CultivationEventDto;
import br.com.vits.orc.vits_agrochain.dto.FinalizeCultivationResponse;
import br.com.vits.orc.vits_agrochain.dto.LotRequest;
import br.com.vits.orc.vits_agrochain.model.CultivationEvent;
import br.com.vits.orc.vits_agrochain.model.Lot;
import br.com.vits.orc.vits_agrochain.repository.CultivationEventRepository;
import br.com.vits.orc.vits_agrochain.repository.LotRepository;
import br.com.vits.orc.vits_agrochain.util.DocumentGeneratorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CultivationEventService {

    private final CultivationEventRepository eventRepository;
    private final LotRepository lotRepository;
    private final DocumentGeneratorUtil documentGeneratorUtil;

    public CultivationEventService(CultivationEventRepository eventRepository,
                                   LotRepository lotRepository,
                                   DocumentGeneratorUtil documentGeneratorUtil) {
        this.eventRepository = eventRepository;
        this.lotRepository = lotRepository;
        this.documentGeneratorUtil = documentGeneratorUtil;
    }

    @Transactional
    public CultivationEvent addEvent(Long lotId, CultivationEventDto dto) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado: " + lotId));

        CultivationEvent event = CultivationEvent.builder()
                .lot(lot)
                .eventType(dto.eventType())
                .description(dto.description())
                .plantingDate(dto.plantingDate() != null ? dto.plantingDate().toLocalDate() : LocalDate.now())
                .estimatedHarvestDate(dto.estimatedHarvestDate() != null ? dto.estimatedHarvestDate().toLocalDate() : LocalDate.now().plusDays(90))
                .build();

        return eventRepository.save(event);
    }

    public List<CultivationEventDto> getEventsByLot(Long lotId) {
        return eventRepository.findByLotLotIdOrderByPlantingDateAsc(lotId).stream()
                .map(e -> new CultivationEventDto(
                        e.getId(),
                        e.getLot().getLotId(),
                        e.getEventType(),
                        e.getDescription(),
                        e.getPlantingDate().atStartOfDay(),
                        e.getEstimatedHarvestDate() != null ? e.getEstimatedHarvestDate().atStartOfDay() : null
                )).collect(Collectors.toList());
    }

    @Transactional
    public FinalizeCultivationResponse finalizeCultivation(Long lotId, LotRequest request) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado: " + lotId));

        // Atualizar Lote (status 1 = Finalizado e aplicar dados da request)
        lot.setLotStatus(1);
        lot.setTotalProduction(request.totalProduction());
        lot.setTotalCost(request.totalCost());
        lot.setSalePrice(request.salePrice());
        lotRepository.save(lot);

        // Buscar histórico de eventos
        List<CultivationEvent> events = eventRepository.findByLotLotIdOrderByPlantingDateAsc(lotId);
        
        List<String> eventSummaries = events.stream()
                .map(e -> String.format("[%s] - %s: %s", 
                        e.getPlantingDate(), 
                        e.getEventType(), 
                        e.getDescription() != null ? e.getDescription() : "Sem descrição"))
                .collect(Collectors.toList());

        // Calcular Totais e Financeiros
        Double receitaEstimada = request.totalProduction() * request.salePrice();
        Double lucroEstimado = receitaEstimada - request.totalCost();

        // Adicionar o sumário final da colheita no relatório
        eventSummaries.add(String.format("FINALIZAÇÃO DO CULTIVO - Receita: R$ %.2f | Lucro: R$ %.2f", receitaEstimada, lucroEstimado));

        // Gerar Dados para Validação
        String combinedDataForHash = "LOTE:" + lotId + "-PROD:" + request.totalProduction() + "-LUCRO:" + lucroEstimado;
        String hashTransacao = documentGeneratorUtil.generateSHA256Hash(combinedDataForHash);
        
        String qrCodeData = "Autenticidade Vits Agrochain | Hash do Cultivo: " + hashTransacao;
        String qrCodeBase64 = "data:image/png;base64," + documentGeneratorUtil.generateQRCodeBase64(qrCodeData, 300, 300);
        
        String lotNameStr = "Lote " + lot.getLotNumber();
        String pdfBase64 = "data:application/pdf;base64," + documentGeneratorUtil.generatePDFBase64(lotNameStr, eventSummaries, qrCodeBase64, hashTransacao);
        String txtBase64 = "data:text/plain;base64," + documentGeneratorUtil.generateTXTBase64(lotNameStr, eventSummaries, hashTransacao);

        return FinalizeCultivationResponse.builder()
                .transactionHash(hashTransacao)
                .qrCodeBase64(qrCodeBase64)
                .pdfBase64(pdfBase64)
                .txtBase64(txtBase64)
                .build();
    }
}
