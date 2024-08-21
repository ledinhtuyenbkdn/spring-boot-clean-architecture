package com.example.springbootcleanarchitecture.module.org.application.service;

import com.example.springbootcleanarchitecture.common.annotation.UseCase;
import com.example.springbootcleanarchitecture.common.filter.LongFilter;
import com.example.springbootcleanarchitecture.module.org.application.port.in.QueryOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.out.QueryOrgPort;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import com.example.springbootcleanarchitecture.module.org.domain.query.OrgCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@UseCase
@Slf4j
public class QueryOrgService implements QueryOrgUseCase {

    private final QueryOrgPort queryOrgPort;

    public QueryOrgService(QueryOrgPort queryOrgPort) {
        this.queryOrgPort = queryOrgPort;
    }

    @Override
    public Optional<Org> findById(Long id) {
        LongFilter idFilter = new LongFilter();
        idFilter.setEquals(id);
        OrgCriteria criteria = new OrgCriteria();
        criteria.setId(idFilter);
        return queryOrgPort.findByCriteria(criteria);
    }

    @Override
    public Optional<Org> findByCriteria(OrgCriteria criteria) {
        return queryOrgPort.findByCriteria(criteria);
    }

    @Override
    public List<Org> findListByCriteria(OrgCriteria criteria) {
        return queryOrgPort.findListByCriteria(criteria);
    }

    @Override
    public Page<Org> findPageByCriteria(OrgCriteria criteria, Pageable pageable) {
        return queryOrgPort.findPageByCriteria(criteria, pageable);
    }

    @Override
    public long countByCriteria(OrgCriteria criteria) {
        return queryOrgPort.countByCriteria(criteria);
    }
}
