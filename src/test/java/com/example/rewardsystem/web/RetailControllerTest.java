package com.example.rewardsystem.web;

import com.example.rewardsystem.dataaccess.repository.PurchaseJpaRepository;
import com.example.rewardsystem.web.dto.CreatePurchaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class RetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PurchaseJpaRepository purchaseJpaRepository;


    @Test
    void rewardSystemCalculatesTotalPoints() throws Exception {

        // when - then
        mockMvc.perform(get("/retail/reward/user/{userId}", 3)
                        .param("mode", "TOTAL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("""
                        {
                          "totalPoints": 691
                        }
                        """));
    }

    @Test
    void rewardSystemCalculatesPointsPerMonth() throws Exception {

        // when - then
        mockMvc.perform(get("/retail/reward/user/{userId}", 3)
                        .param("mode", "MONTHLY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("""
                        {
                          "pointsPerMonth": {
                            "March-2023": 151,
                            "February-2023": 540
                          }
                        }
                        """));
    }

    @Test
    void rewardSystemCalculatesReturns404ForUnknownUser() throws Exception {

        // given
        long unknownUser = 1234567;

        // when - then
        mockMvc.perform(get("/retail/reward/user/{userId}", unknownUser)
                        .param("mode", "MONTHLY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void shouldManagePurchase() throws Exception {

        // SHOULD CREATE A NEW PURCHASE
        // when
        var mvcResult = mockMvc.perform(post("/retail/purchase/user/{userId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "item": "LAPTOP",
                                  "amount": 20,
                                  "date": "2023-06-08"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        long purchaseId = objectMapper.readValue(responseBody, CreatePurchaseResponse.class).getPurchaseId();

        var purchaseJpaEntity = purchaseJpaRepository.findById(purchaseId).orElseThrow();
        assertEquals(purchaseId, purchaseJpaEntity.getId());
        assertEquals("LAPTOP", purchaseJpaEntity.getItem());
        assertEquals(20, purchaseJpaEntity.getAmount());
        assertEquals(LocalDate.of(2023, 6, 8), purchaseJpaEntity.getDate());


        // SHOULD UPDATE THE PURCHASE
        // when
        mockMvc.perform(put("/retail/purchase/{purchaseId}", purchaseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "item": "NICE_LAPTOP",
                                  "amount": 356,
                                  "date": "2023-07-08"
                                }
                                """))
                .andExpect(status().isOk());

        // then
        var updatedEntity = purchaseJpaRepository.findById(purchaseId).orElseThrow();
        assertEquals(purchaseId, updatedEntity.getId());
        assertEquals("NICE_LAPTOP", updatedEntity.getItem());
        assertEquals(356, updatedEntity.getAmount());
        assertEquals(LocalDate.of(2023, 7, 8), updatedEntity.getDate());
    }
}
