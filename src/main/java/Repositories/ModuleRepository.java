package Repositories;

import Beans.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByName(String name);
    List<Module> findByProfId(Long profId);
}