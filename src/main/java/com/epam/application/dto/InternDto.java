package com.epam.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InternDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @NotBlank(message = "Name field can't be empty.")
    private String name;

    @NotBlank(message = "Email field can't be empty.")
    private String email;

}
