package com.dg.ums.repository;

import com.dg.ums.entities.DGUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository  extends JpaRepository<DGUserEntity, Integer> {

    Optional<DGUserEntity> findByUsername(String username);

}
