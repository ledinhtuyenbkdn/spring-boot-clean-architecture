package com.example.springbootcleanarchitecture.module.org.application.service;

import com.example.springbootcleanarchitecture.common.annotation.UseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.in.DeleteOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.out.OrgPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@Slf4j
public class DeleteOrgService implements DeleteOrgUseCase {

    private final OrgPort orgPort;

    public DeleteOrgService(OrgPort orgPort) {
        this.orgPort = orgPort;
    }

    @Override
    public void deleteById(Long orgId) {
        log.info("Deleting org: {}", orgId);
        orgPort.deleteById(orgId);
        log.info("Deleted org: {}", orgId);
    }
}
