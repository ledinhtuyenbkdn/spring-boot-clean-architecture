package com.example.springbootcleanarchitecture.module.org.adapter.out.persistence;

import com.example.springbootcleanarchitecture.common.annotation.PersistenceAdapter;
import com.example.springbootcleanarchitecture.module.org.application.port.out.OrgPort;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import com.example.springbootcleanarchitecture.repository.OrgRepository;

@PersistenceAdapter
public class OrgAdapter implements OrgPort {

    private final OrgRepository orgRepository;

    private final OrgMapper orgMapper;

    public OrgAdapter(OrgRepository orgRepository, OrgMapper orgMapper) {
        this.orgRepository = orgRepository;
        this.orgMapper = orgMapper;
    }

    @Override
    public Org save(Org org) {
        return orgMapper.toDomainModel(orgRepository.save(orgMapper.toEntity(org)));
    }

    @Override
    public void deleteById(Long id) {
        orgRepository.deleteById(id);
    }
}
