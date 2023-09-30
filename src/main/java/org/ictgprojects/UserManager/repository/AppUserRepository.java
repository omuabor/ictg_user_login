package org.ictgprojects.UserManager.repository;

import org.ictgprojects.UserManager.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, String> {

   AppUser findByEncryptedPasswordAndUserId(String encryptedPassword, String userId);

   AppUser findByUserId(String userId);

    List<AppUser> findAllByAccountDisabledFalse();
}
