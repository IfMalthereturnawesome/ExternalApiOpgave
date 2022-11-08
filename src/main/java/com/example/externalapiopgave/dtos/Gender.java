package com.example.externalapiopgave.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Gender {

    private int count;
    private String gender;
    private String name;
    private double probability;
}
