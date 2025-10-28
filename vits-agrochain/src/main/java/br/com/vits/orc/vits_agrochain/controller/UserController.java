package br.com.vits.orc.vits_agrochain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.vits.orc.vits_agrochain.model.User;
import br.com.vits.orc.vits_agrochain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema Verdantis")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna lista completa de usuários cadastrados")
    public List<User> listAll() {
        log.info("Listando todos os usuários");
        return userService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    public User getById(@PathVariable Long id) {
        log.info("Buscando usuário com id: {}", id);
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema (produtor, gestor ou comprador)")
    public User createUser(@RequestBody @Valid User user) {
        log.info("Criando usuário: {}", user);
        return userService.createUser(user);
    }
}