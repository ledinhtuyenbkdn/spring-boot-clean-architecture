package com.example.springbootcleanarchitecture.module.org.domain.query;

import com.example.springbootcleanarchitecture.common.filter.LongFilter;
import com.example.springbootcleanarchitecture.common.filter.StringFilter;
import lombok.Data;

@Data
public class OrgCriteria {

    private LongFilter id;

    private StringFilter name;
}
