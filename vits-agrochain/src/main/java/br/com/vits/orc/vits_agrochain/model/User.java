package br.com.vits.orc.vits_agrochain.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.vits.orc.vits_agrochain.controller.UserController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VITS_ORC_USUARIO")
public class User implements UserDetails {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,  
        generator = "usuario_seq_gen"        
    )
    @SequenceGenerator(
        name = "usuario_seq_gen", 
        sequenceName = "SEQ_USUARIO",     
        allocationSize = 1
    )
    @Column(name = "vits_id_usuario")
    private Long userId;

    @NotBlank
    @Size(max = 200)
    @Column(name = "vits_nome_usuario")
    private String name;

    @Size(max = 14)
    @Column(name = "vits_cpf_usuario", unique = true)
    private String cpf;

    @NotBlank
    @Column(name = "vits_email_usuario")
    private String email;

    @NotBlank
    @Column(name = "vits_senha_usuario")
    private String password;

    @NotNull
    @Column(name = "vits_data_cadastro")
    private LocalDateTime registrationDate;    
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_usuario") 
    private UserType userType;

    public EntityModel<User> toEntityModel() {
        return EntityModel.of(this)
                .add(linkTo(methodOn(UserController.class)
                        .getById(this.userId)).withSelfRel());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userType != null) {
            return List.of(new SimpleGrantedAuthority("ROLE_" + this.userType.getUserDescription().toUpperCase()));
        }
        // Se não tiver UserType, usa ROLE_PRODUTOR por padrão
        return List.of(new SimpleGrantedAuthority("ROLE_PRODUTOR"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}