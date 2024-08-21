package com.example.springbootcleanarchitecture.util;

import com.example.springbootcleanarchitecture.common.filter.LongFilter;
import com.example.springbootcleanarchitecture.common.filter.StringFilter;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {

    private SpecificationUtils() {

    }

    public static <E> Specification<E> buildLongSpecification(LongFilter filter, SingularAttribute<E, Long> attribute) {
        if (filter.getEquals() != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute), filter.getEquals());
        }

        if (filter.getNotEquals() != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute), filter.getNotEquals());
        }

        return Specification.where(null);
    }

    public static <E> Specification<E> buildStringSpecification(StringFilter filter, SingularAttribute<E, String> attribute) {
        if (filter.getEquals() != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute), filter.getEquals());
        }

        if (filter.getNotEquals() != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute), filter.getNotEquals());
        }

        if (filter.getContains() != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(attribute), "%" + filter.getContains() + "%");
        }

        if (filter.getNotContains() != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.notLike(root.get(attribute), "%" + filter.getContains() + "%");
        }

        return Specification.where(null);
    }
}
