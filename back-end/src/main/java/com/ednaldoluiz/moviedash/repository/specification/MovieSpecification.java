package com.ednaldoluiz.moviedash.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.ednaldoluiz.moviedash.model.Movie;

@Repository
public class MovieSpecification {

    public static Specification<Movie> compareField(String fieldName, Double value, boolean greaterThan) {
        return (root, query, criteriaBuilder) -> {
            if (greaterThan) {
                return criteriaBuilder.greaterThan(root.get(fieldName), value);
            } 
            return criteriaBuilder.lessThan(root.get(fieldName), value);
        };
    }

    public static Specification<Movie> releasedBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.between(root.get("releaseDate"), startDate, endDate);
        };
    }

    public static Specification<Movie> languageEquals(String language) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("language"), language);
        };
    }
}