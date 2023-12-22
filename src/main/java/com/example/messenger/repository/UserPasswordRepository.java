package com.example.messenger.repository;
import com.example.messenger.entity.Code;
import com.example.messenger.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public interface UserPasswordRepository extends JpaRepository<UserPassword, UUID> {
    @Query(nativeQuery = false, value = """
   SELECT up FROM UserPassword up
                       INNER JOIN UserEntity u ON up.user.id = u.id
   WHERE up.code = :password AND u.id = :userId
""")
    Optional<UserPassword> getUserPasswordById(UUID userId, String password);
}
