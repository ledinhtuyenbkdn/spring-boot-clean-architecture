package com.example.springbootcleanarchitecture.module.org.application.port.in;

import com.example.springbootcleanarchitecture.module.org.domain.command.CreateOrgCommand;
import com.example.springbootcleanarchitecture.module.org.domain.command.UpdateOrgCommand;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;

public interface SaveOrgUseCase {

    Org create(CreateOrgCommand command);

    Org update(UpdateOrgCommand command);
}
