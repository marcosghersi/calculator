package org.math.calculator.infraestructure.rest;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleOperationsOKTest() throws Exception {
        this.mockMvc.perform(get("/calculator/ADDITION/2/2"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(4));

        this.mockMvc.perform(get("/calculator/SUBTRACTION/2/2"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(0))
                .andReturn().getResponse().getContentAsString();

        final String addResult = this.mockMvc.perform(get("/calculator/ADDITION/2.12345/2"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(4.12345))
                .andReturn().getResponse().getContentAsString();

        BigDecimal addValueResult = BigDecimal.valueOf((double) JsonPath.read(addResult, "$.result"));
        assertEquals(6, addValueResult.precision());
        assertEquals(5, addValueResult.scale());

        final String subResult = this.mockMvc.perform(get("/calculator/SUBTRACTION/0.55/2"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(-1.45))
                .andReturn().getResponse().getContentAsString();

        BigDecimal subValueResult = BigDecimal.valueOf((double) JsonPath.read(subResult, "$.result"));
        assertEquals(3, subValueResult.precision());
        assertEquals(2, subValueResult.scale());

    }

    @Test
    public void simpleOperationsPrecisionTest() throws Exception {
        final String addResult = this.mockMvc.perform(get("/calculator/ADDITION/2.123/2").param("precision", "2"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(4.12))
                .andReturn().getResponse().getContentAsString();

        BigDecimal addValueResult = BigDecimal.valueOf((double) JsonPath.read(addResult, "$.result"));
        assertEquals(3, addValueResult.precision());
        assertEquals(2, addValueResult.scale());

        final String subResult = this.mockMvc.perform(get("/calculator/SUBTRACTION/2.123/2").param("precision", "1"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(0.1))
                .andReturn().getResponse().getContentAsString();

        BigDecimal subValueResult = BigDecimal.valueOf((double) JsonPath.read(subResult, "$.result"));
        assertEquals(1, subValueResult.precision());
        assertEquals(1, subValueResult.scale());

    }

    @Test
    public void simpleOperationsPrecisionAndRoundingModeTest() throws Exception {
        String addResult = this.mockMvc.perform(get("/calculator/ADDITION/5/0.5")
                        .param("precision", "0")
                        .param("roundingMode", "HALF_DOWN"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(5))
                .andReturn().getResponse().getContentAsString();

        BigDecimal addValueResult = new BigDecimal(JsonPath.read(addResult, "$.result").toString());
        assertEquals(1, addValueResult.precision());
        assertEquals(0, addValueResult.scale());

        addResult = this.mockMvc.perform(get("/calculator/ADDITION/5/0.5")
                        .param("precision", "0")
                        .param("roundingMode", "UP"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(6))
                .andReturn().getResponse().getContentAsString();

        addValueResult = new BigDecimal(JsonPath.read(addResult, "$.result").toString());
        assertEquals(1, addValueResult.precision());
        assertEquals(0, addValueResult.scale());

        String subResult = this.mockMvc.perform(get("/calculator/SUBTRACTION/3.5/1")
                        .param("precision", "0")
                        .param("roundingMode", "HALF_DOWN"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(2))
                .andReturn().getResponse().getContentAsString();

        BigDecimal subValueResult = new BigDecimal(JsonPath.read(subResult, "$.result").toString());
        assertEquals(1, subValueResult.precision());
        assertEquals(0, subValueResult.scale());

        subResult = this.mockMvc.perform(get("/calculator/SUBTRACTION/3.5/1")
                        .param("precision", "0")
                        .param("roundingMode", "UP"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(3))
                .andReturn().getResponse().getContentAsString();

        subValueResult = new BigDecimal(JsonPath.read(subResult, "$.result").toString());
        assertEquals(1, subValueResult.precision());
        assertEquals(0, subValueResult.scale());

    }

    @Test
    public void getNotFoundtDueToOneUriParamLeft() throws Exception {
        this.mockMvc.perform(get("/calculator/SUBTRACTION/3.5"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getIllegalArgumentExceptionByPrecisionUnderZero() throws Exception {
        this.mockMvc.perform(get("/calculator/SUBTRACTION/3.5/1")
                        .param("precision", "-3"))
                .andExpect(status().isBadRequest());

    }

}
