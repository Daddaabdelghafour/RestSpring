package Repositories;

import Beans.Prof;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfRepository extends JpaRepository<Prof, Long> {
    List<Prof> findByFirstName(String firstName);
    List<Prof> findByLastName(String lastName);
    Prof findByEmail(String email);
}