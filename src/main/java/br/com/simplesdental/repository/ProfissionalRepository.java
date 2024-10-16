package br.com.simplesdental.repository;

import br.com.simplesdental.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rafael.Witt
 */
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    
}
