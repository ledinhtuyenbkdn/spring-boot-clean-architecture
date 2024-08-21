package com.example.springbootcleanarchitecture.module.org.application.port.out;

import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import com.example.springbootcleanarchitecture.module.org.domain.query.OrgCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QueryOrgPort {

    Optional<Org> findByCriteria(OrgCriteria criteria);

    List<Org> findListByCriteria(OrgCriteria criteria);

    Page<Org> findPageByCriteria(OrgCriteria criteria, Pageable pageable);

    long countByCriteria(OrgCriteria criteria);
}
