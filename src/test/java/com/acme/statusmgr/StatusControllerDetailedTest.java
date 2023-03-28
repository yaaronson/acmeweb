/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the detailed sever status request, with various combinations.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StatusControllerDetailedTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Since this is a test, specify that all tests should use dummy system data.
     */
    @BeforeAll
    public static void beforeAll() {
       //todo StatusController.setSystemInfoFacade(null /* todo: Inject appropriate object */);
    }

    @Test
    public void detailedNameOnly() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(1))
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void detailedDetailsOnly() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.requestCost").value(4))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    @Test
    public void detailedDetailsBeforeName() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(4))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    @Test
    public void detailedRepeatedDetail() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=" +
                        "availableProcessors,availableProcessors"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(7))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and there are 4 processors available, and there are 4 processors available"));
    }

    @Test
    public void detailedAllDetails() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=" +
                        "availableProcessors,freeJVMMemory,totalJVMMemory,jreVersion,tempLocation"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(72))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and there are 4 processors available, and there are 127268272 bytes " +
                                "of JVM memory free, and there is a total of 159383552 bytes of JVM memory, and the JRE version" +
                                " is 15.0.2+7-27, and the server's temp file location is M:\\\\AppData\\\\Local\\\\Temp"));
    }

    @Test
    public void detailedAllDetailsReverseOrder() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=" +
                        "tempLocation,jreVersion,totalJVMMemory,freeJVMMemory,availableProcessors"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(72))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and the server's temp file location is M:\\\\AppData\\\\Local\\\\Temp" +
                                ", and the JRE version is 15.0.2+7-27, and there is a total of 159383552 bytes of JVM memory" +
                                ", and there are 127268272 bytes of JVM memory free, and there are 4 processors available"));
    }

    @Test
    public void detailedInvalidDetail() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=JunkStatus"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Invalid details option: JunkStatus")));
    }

    @Test
    public void detailedInvalidDetailAfterValidDetail() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=availableProcessors,networkBandwidth"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Invalid details option: networkBandwidth")));
    }

}
