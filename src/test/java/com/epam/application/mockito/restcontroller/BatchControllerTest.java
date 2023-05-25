package com.epam.application.mockito.restcontroller;

import com.epam.application.dto.BatchDto;
import com.epam.application.model.Batch;
import com.epam.application.service.BatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BatchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BatchService batchServiceImpl;

    @Mock
    ModelMapper modelMapper;

    static BatchDto batchDto;
    static Batch batch;

    @BeforeAll
    static void setUp() {
        batchDto = BatchDto.builder()
                .id(111l)
                .name("sasi")
                .technology("java")
                .interns(new ArrayList<>())
                .build();
        batch = Batch.builder()
                .id(111l)
                .name("sasi")
                .technology("java")
                .interns(new ArrayList<>())
                .build();
    }

    @Test
    void createBatchTest() throws Exception {
        Mockito.when(batchServiceImpl.addBatch(batchDto)).thenReturn(new BatchDto());
        Mockito.when(modelMapper.map(batchDto, Batch.class)).thenReturn(new Batch());

        mockMvc.perform(post("/batch/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(batchDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getBatchDetailsTest() throws Exception {
        Mockito.when(batchServiceImpl.getBatchDetails(111l)).thenReturn(batchDto);
        Mockito.when(modelMapper.map(batch, BatchDto.class)).thenReturn(batchDto);

        mockMvc.perform(get("/batch/111"))
                .andExpect(status().isOk());
    }

    @Test
    void modifyBatchTest() throws Exception {
        Mockito.when(batchServiceImpl.updateBatch(batchDto)).thenReturn(batchDto);
        Mockito.when(modelMapper.map(batchDto, Batch.class)).thenReturn(new Batch());

        mockMvc.perform(put("/batch/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(batchDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBatchTest() throws Exception {
        Mockito.doNothing().when(batchServiceImpl).deleteBatch(111l);

        mockMvc.perform(delete("/batch/delete/111"))
                .andExpect(status().isNoContent());
    }

}
