package com.example.springbootcleanarchitecture.module.org.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<OrgEntity, Long>, JpaSpecificationExecutor<OrgEntity> {
}
