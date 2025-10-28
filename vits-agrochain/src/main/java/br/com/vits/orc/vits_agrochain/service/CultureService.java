package br.com.vits.orc.vits_agrochain.service;

import org.springframework.stereotype.Service;
import java.util.List;

import br.com.vits.orc.vits_agrochain.model.Culture;
import br.com.vits.orc.vits_agrochain.repository.CultureRepository;

@Service
public class CultureService {

    private final CultureRepository cultureRepository;

    public CultureService(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }

    public Culture createCulture(Culture culture) {
        return cultureRepository.save(culture);
    }

    public List<Culture> listAll() {
        return cultureRepository.findAll();
    }

    public Culture getCultureById(Long id) {
        return cultureRepository.findById(id).orElse(null);
    }
}
