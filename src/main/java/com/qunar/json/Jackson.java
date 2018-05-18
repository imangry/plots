package com.qunar.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Jackson {
    private static final ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        final IsapiResponse response = mapper.readValue(" {\"status\": \"success\", \"msg\": \"\\u6210\\u529f\", \"data\": {\"empl_class\": \"\\u6b63\\u5f0f\", \"em_mail\": \"yuanling.he@qunar.com\", \"em_id\": \"Q000308\", \"hr_status\": \"\\u5728\\u804c\", \"em_name\": \"\\u8d3a\\u6e0a\\u51cc\"}, \"status_id\": 0}", IsapiResponse.class);

        System.out.println(mapper.writeValueAsString(response));

    }
}
