package com.example.springbootcleanarchitecture.module.org.application.service;

import com.example.springbootcleanarchitecture.common.annotation.UseCase;
import com.example.springbootcleanarchitecture.module.org.adapter.out.persistence.OrgRepository;
import com.example.springbootcleanarchitecture.module.org.application.port.in.QueryOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.in.SaveOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.out.OrgPort;
import com.example.springbootcleanarchitecture.module.org.domain.command.CreateOrgCommand;
import com.example.springbootcleanarchitecture.module.org.domain.command.UpdateOrgCommand;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@Slf4j
public class SaveOrgService implements SaveOrgUseCase {

    private final OrgPort orgPort;

    private final QueryOrgUseCase queryOrgUseCase;

    private final ObjectMapper objectMapper;

    public SaveOrgService(OrgPort orgPort, QueryOrgUseCase queryOrgUseCase, ObjectMapper objectMapper) {
        this.orgPort = orgPort;
        this.queryOrgUseCase = queryOrgUseCase;
        this.objectMapper = objectMapper;
    }

    @Override
    public Org create(CreateOrgCommand command) {
        log.info("Creating org: {}", command);
        Org org = objectMapper.convertValue(command, Org.class);
        org = orgPort.save(org);
        log.info("Created org: {}", command);
        return org;
    }

    @Override
    public Org update(UpdateOrgCommand command) {
        log.info("Updating org: {}", command);
        queryOrgUseCase.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("Not found org with id: " + command.getId()));
        Org org = objectMapper.convertValue(command, Org.class);
        org = orgPort.save(org);
        log.info("Updated org: {}", command);
        return org;
    }
}
