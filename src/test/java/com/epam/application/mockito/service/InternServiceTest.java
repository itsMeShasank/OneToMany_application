package com.epam.application.mockito.service;

import com.epam.application.dao.StudentRepository;
import com.epam.application.dto.InternDto;
import com.epam.application.helper.InternException;
import com.epam.application.model.Intern;
import com.epam.application.service.InternServiceImpl;
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
import org.modelmapper.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class InternServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    TypeToken<List<InternDto>> internTypeToken;

    @InjectMocks
    InternServiceImpl internServiceImpl;

    static Intern intern;
    static InternDto internDto;

    @BeforeAll
    static void setUp() {
        intern = Intern.builder()
                .id(1l)
                .name("sasi")
                .email("sasi@epam.com")
                .build();

        internDto = InternDto.builder()
                .id(1l)
                .name("sasi")
                .email("sasi@epam.com")
                .build();
    }

    @Test
    void createInternTest() {

        Mockito.when(studentRepository.save(intern)).thenReturn(intern);
        Mockito.when(modelMapper.map(internDto,Intern.class)).thenReturn(intern);

        assertEquals(internDto,internServiceImpl.addIntern(internDto));
    }

    @Test
    void deleteTest() {
        Mockito.doNothing().when(studentRepository).deleteById(1l);
        internServiceImpl.deleteIntern(1l);
    }

    @Test
    void updateTest() {
        Mockito.when(internServiceImpl.updateIntern(internDto)).thenReturn(internDto);
        Mockito.when(modelMapper.map(internDto,Intern.class)).thenReturn(intern);

        assertEquals(internDto,internServiceImpl.updateIntern(internDto));
    }

    @Test
    void getDetailsTest() {
        Mockito.when(studentRepository.findById(1l)).thenReturn(Optional.ofNullable(intern));
        Mockito.when(modelMapper.map(intern,InternDto.class)).thenReturn(internDto);
        assertEquals(internDto,internServiceImpl.getInternDetails(1l));
    }

    @Test
    void getInternExceptionTest() {
        Mockito.when(studentRepository.findById(1l)).thenThrow(InternException.class);
        assertThrows(InternException.class,() ->internServiceImpl.getInternDetails(1l));
    }

    @Test
    void getAllInternsTest() {
        List<Intern> interns=new ArrayList<>();
        interns.add(intern);
        List<InternDto> internDtos=new ArrayList<>(Arrays.asList(internDto));
        Mockito.when(studentRepository.findAll()).thenReturn(interns);
        Mockito.when(modelMapper.map(interns,internTypeToken.getType())).thenReturn(internDtos);
        assertEquals(internDtos,internServiceImpl.getInterns());
    }

    @Test
    void getInternsExceptionTest() {
        Mockito.when(studentRepository.findAll()).thenThrow(InternException.class);
        assertThrows(InternException.class,() ->internServiceImpl.getInterns());
    }

}
