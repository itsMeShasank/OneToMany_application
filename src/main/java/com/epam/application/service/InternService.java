package com.epam.application.service;

import com.epam.application.dto.InternDto;
import com.epam.application.helper.InternException;

import java.util.List;

public interface InternService {

    InternDto addIntern(InternDto internDto);
    void deleteIntern(Long id);
    InternDto updateIntern(InternDto internDto);
    InternDto getInternDetails(Long id) throws InternException;

    List<InternDto> getInterns() throws InternException;

}
