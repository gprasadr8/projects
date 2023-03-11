package com.dg.bcs.persistence.repo;

import com.dg.bcs.persistence.model.BookCoverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCoverRepository extends JpaRepository<BookCoverEntity,String> {

}
