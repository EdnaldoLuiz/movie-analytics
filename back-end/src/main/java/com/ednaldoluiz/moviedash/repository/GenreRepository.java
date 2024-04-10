package com.ednaldoluiz.moviedash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednaldoluiz.moviedash.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{}
