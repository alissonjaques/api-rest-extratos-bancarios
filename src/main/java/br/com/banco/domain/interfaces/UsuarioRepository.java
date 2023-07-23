package br.com.banco.domain.interfaces;

import br.com.banco.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    @Query("""
            select u from Usuario u
            where u.idUsuario = :idUsuario
           """)
    Usuario findByIdUsuario(Long idUsuario);
}

