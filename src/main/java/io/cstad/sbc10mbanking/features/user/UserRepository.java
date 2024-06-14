package io.cstad.sbc10mbanking.features.user;

import io.cstad.sbc10mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM users where phoneNumber = ?
    Optional<User> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    // SELECT * FROM users where uuid = ?
    Optional<User> findByUuid(String Uuid);

}
