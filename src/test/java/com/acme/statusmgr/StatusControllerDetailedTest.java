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

import com.acme.statusmgr.beans.*;
import org.hamcrest.Matchers;
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
        availableProcessors.setter(new dummy());
        jreVersion.setter(new dummy());
        totalJVMMemory.setter(new dummy());
        tempLocation.setter(new dummy());
        freeJVMMemory.setter(new dummy());
    }


    /**
     * Tests that the server can handle requests with the "availableProcessors" detail.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testAvailableProcessors() throws Exception{
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(4))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }


    /**
     * Tests that the server can handle requests with the "freeJVMMemory" detail.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testFreeJvmMemory() throws Exception{
        this.mockMvc.perform(get("/server/status/detailed?details=freeJVMMemory&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(8))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 127268272 bytes of JVM memory free"));
    }


    /**
     * Tests that the server can handle requests with the "totalJVMMemory" detail.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testTotalJvmMemory() throws Exception{
        this.mockMvc.perform(get("/server/status/detailed?details=totalJVMMemory&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(14))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there is a total of 159383552 bytes of JVM memory"));
    }


    /**
     * Tests that the server can handle requests with the "jreVersion" detail.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testJreVersion() throws Exception{
        this.mockMvc.perform(get("/server/status/detailed?details=jreVersion&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(20))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the JRE version is 15.0.2+7-27"));

    }


    /**
     * Tests that the server can handle requests with the "tempLocation" detail.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testTempLocation() throws Exception{
        this.mockMvc.perform(get("/server/status/detailed?details=tempLocation&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(30))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the server's temp file location is M:\\\\AppData\\\\Local\\\\Temp"));

    }


    /**
     * Tests that the server can accept multiple details in one HTTP request.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testRequestMultipleDetails() throws Exception {
        this.mockMvc.perform(
                        get("/server/status/detailed?details=tempLocation,totalJVMMemory,availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())

                .andExpect(jsonPath("$.contentHeader")
                        .value("Server Status requested by Yankel"))

                .andExpect(jsonPath("$.requestCost")
                        .value(46))

                .andExpect(jsonPath("$.statusDesc")
                        .value("Server is up, and the server's temp file location is " +
                                "M:\\\\AppData\\\\Local\\\\Temp, and there is a total of 159383552 " +
                                "bytes of JVM memory, and there are 4 processors available"));

    }


    /**
     * Tests that the server does not accept requests without the details parameter.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testDetailsAreRequired() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(Matchers.is(
                        "Required request parameter 'details' for method parameter type List is not present")));

    }


    /**
     * Tests that the server throws an error if the caller asked for a detail
     * that doesn't exist.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testNonExistentDetail() throws Exception { //
        this.mockMvc.perform(get("/server/status/detailed?details=noSuchDetail&name=Yankel"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(Matchers.is(
                        "Invalid details option: noSuchDetail")));
    }


    /**
     * Tests that the server allows multiple requests for the same detail.
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testRepeatedDetails() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=freeJVMMemory,freeJVMMemory&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())

                .andExpect(jsonPath("$.contentHeader")
                        .value("Server Status requested by Yankel"))

                .andExpect(jsonPath("$.requestCost")
                        .value(15))

                .andExpect(jsonPath("$.statusDesc")
                        .value("Server is up, and there are 127268272 bytes of JVM " +
                                "memory free, and there are 127268272 bytes of JVM memory free"));

    }

    /**
     * Tests that details params can come before name
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void detailedDetailsBeforeName() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(4))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    /**
     * Tests that the server allows different/reverse order for details.
     * @throws Exception if something goes wrong while using the mock server
     */
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
    public void detailedInvalidDetail() throws Exception {//
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=JunkStatus"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Invalid details option: JunkStatus")));
    }

    @Test
    public void detailedInvalidDetailAfterValidDetail() throws Exception {//
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=availableProcessors,networkBandwidth"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Invalid details option: networkBandwidth")));
    }

}
