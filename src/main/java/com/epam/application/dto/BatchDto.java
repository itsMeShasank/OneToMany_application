package com.epam.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BatchDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @NotBlank(message = "Name field can't be empty.")
    private String name;
    @NotBlank(message = "Technology field can't be empty.")
    private String technology;
    private List<InternDto> interns = new ArrayList<>();

}
