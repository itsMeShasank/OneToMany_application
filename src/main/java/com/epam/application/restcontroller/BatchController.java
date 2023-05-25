package com.epam.application.restcontroller;

import com.epam.application.dto.BatchDto;
import com.epam.application.helper.BatchException;
import com.epam.application.service.BatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BatchController {

    @Autowired
    BatchService batchServiceImpl;
    @GetMapping("/batch/{id}")
    public ResponseEntity<BatchDto> getBatchDetails(@PathVariable @Valid Long id) throws BatchException {
        return ResponseEntity.status(HttpStatus.OK).body(batchServiceImpl.getBatchDetails(id));
    }

    @PostMapping("/batch/insert")
    public ResponseEntity<BatchDto> addBatch(@RequestBody @Valid BatchDto BatchDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(batchServiceImpl.addBatch(BatchDto));
    }

    @PutMapping("/batch/update")
    public ResponseEntity<BatchDto> modifyBatch(@RequestBody @Valid BatchDto BatchDto) {
        return ResponseEntity.status(HttpStatus.OK).body(batchServiceImpl.updateBatch(BatchDto));
    }

    @DeleteMapping("/batch/delete/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable @Valid Long id) {
        batchServiceImpl.deleteBatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
