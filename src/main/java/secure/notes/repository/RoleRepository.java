package secure.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secure.notes.model.AppRole;
import secure.notes.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
