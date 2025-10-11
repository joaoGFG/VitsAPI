package br.com.vits.orc.vits_agrochain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.vits.orc.vits_agrochain.model.UserType;
import br.com.vits.orc.vits_agrochain.repository.UserTypeRepository;

@Service
public class UserTypeService {
    
    @Autowired
    private UserTypeRepository userTypeRepository;

    public UserType create(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public List<UserType> listAll() {
        return userTypeRepository.findAll();
    }

    public UserType getUserTypeById(Long id) {
        return userTypeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "UserType não encontrado com o id: " + id
                ));
    }
}
