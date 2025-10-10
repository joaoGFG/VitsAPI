package br.com.vits.orc.vits_agrochain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.vits.orc.vits_agrochain.model.User;
import br.com.vits.orc.vits_agrochain.model.UserType;
import br.com.vits.orc.vits_agrochain.repository.UserRepository;
import br.com.vits.orc.vits_agrochain.repository.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users") 
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("Criando usuário: {}", user);

        Long userTypeId = user.getUserType().getUserTypeId();
        
        UserType userTypeOriginal = userTypeRepository.findById(userTypeId)
            .orElseThrow(() -> new EntityNotFoundException("UserType não encontrado com o id: " + userTypeId));

        user.setUserType(userTypeOriginal);

        User savedUser = userRepository.save(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}