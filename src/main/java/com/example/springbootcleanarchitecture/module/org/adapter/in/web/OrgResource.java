package com.example.springbootcleanarchitecture.module.org.adapter.in.web;

import com.example.springbootcleanarchitecture.common.annotation.WebAdapter;
import com.example.springbootcleanarchitecture.common.exception.BadRequestException;
import com.example.springbootcleanarchitecture.module.org.application.port.in.DeleteOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.in.QueryOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.application.port.in.SaveOrgUseCase;
import com.example.springbootcleanarchitecture.module.org.domain.command.CreateOrgCommand;
import com.example.springbootcleanarchitecture.module.org.domain.command.UpdateOrgCommand;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import com.example.springbootcleanarchitecture.module.org.domain.query.OrgCriteria;
import com.example.springbootcleanarchitecture.util.HttpHeaderUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/api/orgs")
@Slf4j
public class OrgResource {
    
    private final SaveOrgUseCase saveOrgUseCase;
    
    private final DeleteOrgUseCase deleteOrgUseCase;
    
    private final QueryOrgUseCase queryOrgUseCase;

    public OrgResource(SaveOrgUseCase saveOrgUseCase, DeleteOrgUseCase deleteOrgUseCase, QueryOrgUseCase queryOrgUseCase) {
        this.saveOrgUseCase = saveOrgUseCase;
        this.deleteOrgUseCase = deleteOrgUseCase;
        this.queryOrgUseCase = queryOrgUseCase;
    }

    @PostMapping("")
    public ResponseEntity<Org> createOrg(@Valid @RequestBody CreateOrgCommand command) throws URISyntaxException {
        log.info("REST request to create Org: {}", command);
        Org org = saveOrgUseCase.create(command);
        return ResponseEntity.created(new URI("/api/orgs/" + org.getId()))
                .body(org);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Org> updateOrg(@Valid @RequestBody UpdateOrgCommand command, @PathVariable Long id) {
        log.info("REST request to update Org: {}", command);
        if (command.getId() == null) {
            throw new BadRequestException("Id must be not null");
        }
        if (!command.getId().equals(id)) {
            throw new BadRequestException("Ids are not same");
        }
        Org org = saveOrgUseCase.update(command);
        return ResponseEntity.ok(org);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Org> getOrg(@PathVariable Long id) {
        log.info("REST request to get Org by Id: {}", id);
        Optional<Org> org = queryOrgUseCase.findById(id);
        return org.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<Org>> getAllOrgs(OrgCriteria criteria, Pageable pageable) {
        log.info("REST request to get all Org by criteria: {}", criteria);
        Page<Org> orgs = queryOrgUseCase.findPageByCriteria(criteria, pageable);
        return ResponseEntity.ok()
                .headers(HttpHeaderUtils.generatePaginationHttpHeaders(orgs))
                .body(orgs.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrg(@PathVariable Long id) {
        log.info("REST request to delete Org with id: {}", id);
        if (id == null) {
            throw new BadRequestException("Id must be not null");
        }
        deleteOrgUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
