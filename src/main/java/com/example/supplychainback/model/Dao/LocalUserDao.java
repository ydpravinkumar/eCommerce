package com.example.supplychainback.model.Dao;

import com.example.supplychainback.model.LocalUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
/**
 * User Repository to CRUD the database table for LocalUser
 */
public interface LocalUserDao extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByEmailIgnoreCase(String email);
    @Query("SELECT l.id FROM LocalUser l WHERE l.username = ?1")
    Long findIdByUsername(String username);
}
