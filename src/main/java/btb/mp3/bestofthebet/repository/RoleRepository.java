package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.EnumRole;
import btb.mp3.bestofthebet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole nameRome);
}
