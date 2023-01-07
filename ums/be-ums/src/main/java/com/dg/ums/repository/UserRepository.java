package com.dg.ums.repository;

import com.dg.ums.entities.DGUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<DGUserEntity, Integer> {
}
