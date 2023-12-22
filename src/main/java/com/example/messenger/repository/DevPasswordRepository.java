package com.example.messenger.repository;

import com.example.messenger.entity.Code;
import com.example.messenger.entity.DevPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface DevPasswordRepository extends JpaRepository<DevPassword, UUID> {
    @Query(nativeQuery = false, value = """
   SELECT up FROM DevPassword up
                       INNER JOIN DeveloperEntity d ON up.developerEntity.id = d.id
   WHERE up.code = :password AND d.id = :userId
""")
    Optional<DevPassword> getUserPasswordById(UUID userId, String password);
}
