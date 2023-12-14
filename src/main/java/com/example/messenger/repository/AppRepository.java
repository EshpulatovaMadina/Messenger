package com.example.messenger.repository;

import com.example.messenger.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AppRepository extends JpaRepository<AppEntity, UUID> {
}
