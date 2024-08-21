package com.example.springbootcleanarchitecture.common.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LongFilter {

    private Long equals;

    private Long notEquals;
}
