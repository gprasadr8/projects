package com.dg.bms.bmssoap.repository;

import com.dg.bms.bmssoap.entity.DBBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<DBBook,Integer> {

}
