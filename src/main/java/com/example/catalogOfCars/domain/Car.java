package com.example.catalogOfCars.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    @NotBlank (message = "field brand  cannot be empty")
    private String brand;
    @NotBlank (message = "field \"number\"  cannot be empty")
    @Valid
    @Pattern(regexp = "^[A-Z][0-9]{3}[A-Z]{2}", message = "invalid number. right format T543EB ")
    private String number;
    private String color;
    @Valid
    @Pattern(regexp = "^[0-9]{4}", message = "invalid year of issue. right format 1998 or 2004 ")
    private String yearOfIssue;
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;
}
