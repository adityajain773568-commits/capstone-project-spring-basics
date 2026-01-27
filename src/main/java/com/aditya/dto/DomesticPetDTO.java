package com.aditya.dto;
import java.time.LocalDate;
import java.util.Objects;

public class DomesticPetDTO extends PetDTO {
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        if (Objects.nonNull(getOwnerDTO())){
            return "DomesticPetDTO{" +
                    "id=" + getId() +
                    ", name='" + getName() + '\'' +
                    ", gender=" + getGender() +
                    ", petType=" + getPetType() +
                    ", birthDate=" + birthDate +
                    ", ownerDTO=" + getOwnerDTO() +
                    '}';
        }else {
            return "DomesticPetDTO{" +
                    "id=" + getId() +
                    ", name='" + getName() + '\'' +
                    ", gender=" + getGender() +
                    ", petType=" + getPetType() +
                    ", birthDate=" + birthDate +
                    '}';
        }

    }
}
