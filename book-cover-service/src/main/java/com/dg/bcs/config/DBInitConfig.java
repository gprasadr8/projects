package com.dg.bcs.config;

import com.dg.bcs.persistence.model.BookCoverEntity;
import com.dg.bcs.persistence.repo.BookCoverRepository;
import com.dg.bcs.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class DBInitConfig {


    @Autowired
    private ImageService imageService;

    private static final String IMAGE_FILE_LOCATION = "classpath:book_covers/%s.jpg";

    @Bean
    CommandLineRunner initBookCoverDatabase(BookCoverRepository bookCoverRepository) {

        return args -> {
            Set<BookCoverEntity> bookCoverEntities =  initBookCovers();
            bookCoverRepository.saveAll(bookCoverEntities);
        };
    }

    private Set<BookCoverEntity> initBookCovers() {
        Set<BookCoverEntity> bookCovers = new HashSet<>();
        bookCovers.add(createBookCover("Clean Code","978-0132350884"));
        bookCovers.add(createBookCover("Thinking in Java","978-0134685983"));
        bookCovers.add(createBookCover("Effective Java 3E","978-0134685991"));
        bookCovers.add(createBookCover("Head First Design Patterns","978-0134685993"));
        bookCovers.add(createBookCover("Head First Java","978-0134685996"));
        bookCovers.add(createBookCover("Spring in Action","978-0134685998"));
        bookCovers.add(createBookCover("TDD","978-0134685999"));
        return bookCovers;
    }

    private BookCoverEntity createBookCover(String title, String isbn) {
        return BookCoverEntity.builder()
                .title(title)
                .isbn(isbn)
                .imageLocation(String.format(IMAGE_FILE_LOCATION,isbn))
                .build();
    }

}
