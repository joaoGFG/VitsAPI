package br.com.vits.orc.vits_agrochain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.vits.orc.vits_agrochain.model.User;
import br.com.vits.orc.vits_agrochain.model.UserType;
import br.com.vits.orc.vits_agrochain.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    private final UserTypeService userTypeService;

    public UserService(UserRepository userRepository, UserTypeService userTypeService) {
        this.userRepository = userRepository;
        this.userTypeService = userTypeService;
    }

    public User createUser(User user) {
        Long userTypeId = user.getUserType().getUserTypeId();
        
        UserType userType = userTypeService.getUserTypeById(userTypeId);

        user.setUserType(userType);

        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Usuário não encontrado com o id: " + id
                ));
    }

}
