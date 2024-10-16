package br.com.simplesdental.repository;

import br.com.simplesdental.entity.Contato;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rafael Witt
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByProfissionalId(Long postId);

    @Transactional
    void deleteByProfissionalId(long profissionalId);

}
