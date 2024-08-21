package com.example.springbootcleanarchitecture.module.org.application.port.out;

import com.example.springbootcleanarchitecture.module.org.domain.model.Org;

public interface OrgPort {

    Org save(Org org);

    void deleteById(Long id);
}
