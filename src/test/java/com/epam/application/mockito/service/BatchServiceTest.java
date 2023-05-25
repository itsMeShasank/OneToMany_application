package com.epam.application.mockito.service;

import com.epam.application.dao.BatchRepository;
import com.epam.application.dto.BatchDto;
import com.epam.application.helper.BatchException;
import com.epam.application.model.Batch;
import com.epam.application.service.BatchServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @Mock
    ModelMapper modelMapper;

    @Mock
    BatchRepository batchRepository;

    @InjectMocks
    BatchServiceImpl batchServiceImpl;
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
    void createBatchTest() {
        Mockito.when(batchRepository.save(batch)).thenReturn(batch);
        Mockito.when(modelMapper.map(batch,BatchDto.class)).thenReturn(batchDto);
        assertEquals(batchDto,batchServiceImpl.addBatch(batchDto));
    }

    @Test
    void deleteBatchTest() {
        Mockito.doNothing().when(batchRepository).deleteById(111l);
        batchServiceImpl.deleteBatch(111l);
    }

    @Test
    void updateBatchTest() {
        Mockito.when(batchRepository.save(batch)).thenReturn(batch);
        Mockito.when(modelMapper.map(batch,BatchDto.class)).thenReturn(batchDto);
        assertEquals(batchDto,batchServiceImpl.updateBatch(batchDto));
    }

    @Test
    void getBatchDetailsTest() {
        Mockito.when(batchRepository.findById(111l)).thenReturn(Optional.ofNullable(batch));
        Mockito.when(modelMapper.map(batch,BatchDto.class)).thenReturn(batchDto);
        assertEquals(batchDto,batchServiceImpl.getBatchDetails(111l));
    }

    @Test
    void getBatchDetailsExceptionTest() {
        Mockito.when(batchRepository.findById(1l)).thenThrow(BatchException.class);
        assertThrows(BatchException.class,() -> batchServiceImpl.getBatchDetails(1l));
    }

}
