package com.example.authservice.commons.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity // Indica que esta clase es una entidad JPA.
@Table(name = "users") // Especifica que esta entidad está mapeada a la tabla "users".
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Esta clase implementa UserDetails para integrarse con el sistema de seguridad de Spring.
public class UserModel implements UserDetails {
    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {

        return email;

    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    // Indica que la cuenta no está bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    // Indica que las credenciales no han expirado.
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    // Indica que la cuenta está habilitada.
    @Override
    public boolean isEnabled() {

        return true;
    }
}
