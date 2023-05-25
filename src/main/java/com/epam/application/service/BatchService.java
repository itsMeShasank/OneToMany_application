package com.epam.application.service;

import com.epam.application.dto.BatchDto;
import com.epam.application.helper.BatchException;

public interface BatchService {
    BatchDto addBatch(BatchDto batchDto);
    void deleteBatch(Long id);
    BatchDto updateBatch(BatchDto batchDto);
    BatchDto getBatchDetails(Long id) throws BatchException;
}
