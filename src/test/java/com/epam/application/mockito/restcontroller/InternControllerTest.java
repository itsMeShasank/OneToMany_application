package com.epam.application.mockito.restcontroller;

import com.epam.application.dto.InternDto;
import com.epam.application.model.Intern;
import com.epam.application.service.InternService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
class InternControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    InternService internServiceImpl;
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
    void testMockito() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void getInternTest() throws Exception {
        Mockito.when(internServiceImpl.getInternDetails(1l)).thenReturn(new InternDto());
        Mockito.when(modelMapper.map(intern,InternDto.class)).thenReturn(internDto);

        mockMvc.perform(get("/intern/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getInternsTest() throws Exception {
        Mockito.when(internServiceImpl.getInterns()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/interns"))
                .andExpect(status().isOk());
    }

    @Test
    void createInternTest() throws Exception {
        Mockito.when(internServiceImpl.addIntern(internDto)).thenReturn(internDto);
        Mockito.when(modelMapper.map(internDto,Intern.class)).thenReturn(intern);

        mockMvc.perform(post("/intern/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(internDto)))
                .andExpect(status().isCreated());

    }
    @Test
    void updateInternTest() throws Exception {
        Mockito.when(internServiceImpl.updateIntern(internDto)).thenReturn(internDto);
        Mockito.when(modelMapper.map(internDto,Intern.class)).thenReturn(intern);

        mockMvc.perform(put("/intern/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(internDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        Mockito.doNothing().when(internServiceImpl).deleteIntern(1l);

        mockMvc.perform(delete("/intern/delete/1"))
                .andExpect(status().isNoContent());
    }
}
