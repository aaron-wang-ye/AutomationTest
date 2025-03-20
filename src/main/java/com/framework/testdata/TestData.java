package com.framework.testdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {
    private String keyword;
    private String expected_title;
    private int expected_result_count;
}