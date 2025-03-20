package com.framework.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.framework.testdata.TestData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConfigReader {
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static List<TestData> getBaiduSearchData() throws IOException {
        // 明确指定返回类型为 Map<String, List<TestData>>
        Map<String, List<TestData>> dataMap = mapper.readValue(
                new File("src/test/resources/testdata.yaml"),
                new TypeReference<Map<String, List<TestData>>>() {}
        );
        return dataMap.get("baidu_search");
    }
}