package br.com.vits.orc.vits_agrochain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vits.orc.vits_agrochain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
