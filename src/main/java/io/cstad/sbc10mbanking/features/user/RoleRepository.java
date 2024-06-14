package io.cstad.sbc10mbanking.features.user;

import io.cstad.sbc10mbanking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
