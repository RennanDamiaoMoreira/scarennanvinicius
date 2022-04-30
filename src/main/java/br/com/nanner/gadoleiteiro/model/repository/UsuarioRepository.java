package br.com.nanner.gadoleiteiro.model.repository;

import br.com.nanner.gadoleiteiro.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByLogin(String login);
}
