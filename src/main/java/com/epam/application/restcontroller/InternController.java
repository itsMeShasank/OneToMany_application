package com.epam.application.restcontroller;

import com.epam.application.dto.InternDto;
import com.epam.application.service.InternService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InternController {

    @Autowired
    InternService internServiceImpl;

    @GetMapping("/")
    public ResponseEntity<String> firstController() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Application..");
    }

    @GetMapping("/intern/{id}")
    public ResponseEntity<InternDto> getIntern(@PathVariable @Valid Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(internServiceImpl.getInternDetails(id));
    }

    @GetMapping("/interns")
    public ResponseEntity<List<InternDto>> getInterns() {
        return ResponseEntity.status(HttpStatus.OK).body(internServiceImpl.getInterns());
    }

    @PostMapping("/intern/insert")
    public ResponseEntity<InternDto> addIntern(@RequestBody @Valid InternDto internDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(internServiceImpl.addIntern(internDto));
    }

    @PutMapping("/intern/update")
    public ResponseEntity<InternDto> updateIntern(@RequestBody @Valid  InternDto internDto) {
        return ResponseEntity.status(HttpStatus.OK).body(internServiceImpl.updateIntern(internDto));
    }

    @DeleteMapping("/intern/delete/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable @Valid Long id) {
        internServiceImpl.deleteIntern(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
