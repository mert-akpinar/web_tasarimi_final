package com.yusufmertakpinar.model.dto;

import com.yusufmertakpinar.model.entity.RegistrationEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDto {
    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull(message = "Email can't be empty")
    private String email;
    @NotNull(message = "Password can't be empty")
    private String password;

    // Entity to Dto
    public static RegistrationDto entityToDto(@NotNull RegistrationEntity entity) {
        return RegistrationDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

}

