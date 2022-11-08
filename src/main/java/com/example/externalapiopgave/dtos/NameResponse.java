package com.example.externalapiopgave.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NameResponse {
    private String name;
    private String gender;
    private double genderProbability;
    private int age;
    private int ageCount;
    private String country;
    private double countryProbability;

    public void setGenderProbability(double genderProbability) {
        this.genderProbability = genderProbability * 100;
    }

    public void setCountryProbability(double countryProbability) {
        this.countryProbability = countryProbability * 100;
    }

}
