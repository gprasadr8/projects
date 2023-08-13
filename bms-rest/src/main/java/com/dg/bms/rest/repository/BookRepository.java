package com.dg.bms.rest.repository;

import com.dg.bms.rest.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer> {

}
