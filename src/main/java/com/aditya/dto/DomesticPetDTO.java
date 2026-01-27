package com.aditya.dto;


import java.time.LocalDate;

public class DomesticPetDTO extends PetDTO{
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", gender=" + getGender() +
                ", petType=" + getPetType() +
                ", ownerDTO=" + getOwnerDTO() +
                "birthDate=" + birthDate +
                '}';
    }
}
