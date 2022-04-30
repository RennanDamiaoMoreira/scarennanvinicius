package br.com.nanner.gadoleiteiro.service;

import br.com.nanner.gadoleiteiro.exception.PassInvalidExpired;

import br.com.nanner.gadoleiteiro.model.entity.Usuario;
import br.com.nanner.gadoleiteiro.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario save(Usuario user) {
        return repository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario user = repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder().username(user.getLogin()).password(user.getPassword()).roles(roles).build();
    }


    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }
}
