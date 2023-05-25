package com.epam.application.service;

import com.epam.application.dao.BatchRepository;
import com.epam.application.dto.BatchDto;
import com.epam.application.helper.BatchException;
import com.epam.application.model.Batch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BatchRepository batchRepository;
    @Override
    public BatchDto addBatch(BatchDto batchDto) {
        batchRepository.save(convertDtoToEntity(batchDto));
        return batchDto;
    }

    @Override
    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }

    @Override
    public BatchDto updateBatch(BatchDto BatchDto) {
        return addBatch(BatchDto);
    }

    @Override
    public BatchDto getBatchDetails(Long id) throws BatchException {
        return batchRepository.findById(id).map(this::convertEntityToDto)
                .orElseThrow(() -> {throw new BatchException("No Batch with respective Id.",HttpStatus.OK);});
    }

    private BatchDto convertEntityToDto(Batch batch) {
        return modelMapper.map(batch,BatchDto.class);
    }
    private Batch convertDtoToEntity(BatchDto batchDto) {
        return modelMapper.map(batchDto,Batch.class);
    }
}
