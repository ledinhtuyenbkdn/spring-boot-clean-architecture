package com.example.springbootcleanarchitecture.module.org.adapter.out.persistence;

import com.example.springbootcleanarchitecture.common.annotation.PersistenceAdapter;
import com.example.springbootcleanarchitecture.common.exception.AdapterException;
import com.example.springbootcleanarchitecture.module.org.application.port.out.QueryOrgPort;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import com.example.springbootcleanarchitecture.module.org.domain.query.OrgCriteria;
import com.example.springbootcleanarchitecture.util.SpecificationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
public class QueryOrgAdapter implements QueryOrgPort {

    private final OrgRepository orgRepository;

    private final OrgMapper orgMapper;

    public QueryOrgAdapter(OrgRepository orgRepository, OrgMapper orgMapper) {
        this.orgRepository = orgRepository;
        this.orgMapper = orgMapper;
    }

    @Override
    public Optional<Org> findByCriteria(OrgCriteria criteria) {
        List<OrgEntity> orgEntities = orgRepository.findAll(createSpecification(criteria));
        if (CollectionUtils.isEmpty(orgEntities)) {
            return Optional.empty();
        }
        if (orgEntities.size() > 1) {
            int size = orgEntities.size();
            Set<Long> ids = orgEntities.stream().map(OrgEntity::getId).collect(Collectors.toSet());
            throw new AdapterException("Found more than single result org: Size: " + size + " Ids: " + ids);
        }
        return Optional.of(orgEntities.get(0)).map(orgMapper::toDomainModel);
    }

    @Override
    public List<Org> findListByCriteria(OrgCriteria criteria) {
        return orgRepository.findAll(createSpecification(criteria))
                .stream()
                .map(orgMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Org> findPageByCriteria(OrgCriteria criteria, Pageable pageable) {
        Page<OrgEntity> orgEntityPage = orgRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                orgMapper.toDomainModel(orgEntityPage.getContent()),
                pageable,
                orgEntityPage.getTotalElements()
        );
    }

    @Override
    public long countByCriteria(OrgCriteria criteria) {
        return orgRepository.count(createSpecification(criteria));
    }

    private Specification<OrgEntity> createSpecification(OrgCriteria criteria) {
        Specification<OrgEntity> specification = Specification.where(null);

        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(SpecificationUtils.buildLongSpecification(criteria.getId(), OrgEntity_.id));
            }

            if (criteria.getName() != null) {
                specification = specification.and(SpecificationUtils.buildStringSpecification(criteria.getName(), OrgEntity_.name));
            }
        }
        return specification;
    }
}
