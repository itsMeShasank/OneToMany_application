package com.epam.application.service;

import com.epam.application.dao.StudentRepository;
import com.epam.application.dto.InternDto;
import com.epam.application.helper.InternException;
import com.epam.application.model.Intern;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternServiceImpl implements InternService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TypeToken<List<InternDto>> internTypeToken;

    @Override
    public InternDto addIntern(InternDto internDto) {
        studentRepository.save(convertDtoToEntity(internDto));
        return internDto;
    }

    @Override
    public void deleteIntern(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public InternDto updateIntern(InternDto internDto) {
        return addIntern(internDto);
    }

    @Override
    public InternDto getInternDetails(Long id)throws InternException {
        return studentRepository.findById(id).map(this::convertEntityToDto)
                .orElseThrow(() -> {throw new InternException("No Intern found with respective id.",HttpStatus.BAD_REQUEST);});
    }

    @Override
    public List<InternDto> getInterns() throws InternException{
        return (List<InternDto>) Optional.ofNullable(modelMapper.map(studentRepository.findAll(),internTypeToken.getType()))
                .orElseThrow(()->{throw new InternException("No DataFound.",HttpStatus.BAD_REQUEST);});
    }

    private InternDto convertEntityToDto(Intern intern) {
        return modelMapper.map(intern,InternDto.class);
    }
    private Intern convertDtoToEntity(InternDto internDto) {
        return modelMapper.map(internDto,Intern.class);
    }
}
