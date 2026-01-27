package com.aditya.repository.impl;

import com.aditya.dto.DomesticPetDTO;
import com.aditya.dto.OwnerDTO;
import com.aditya.dto.PetDTO;
import com.aditya.dto.WildPetDTO;
import com.aditya.enums.Gender;
import com.aditya.repository.PetRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.aditya.enums.Gender.F;
import static com.aditya.enums.Gender.M;
import static com.aditya.enums.PetType.CAT;
import static com.aditya.enums.PetType.DOG;

@Repository
public class PetRepositoryImpl implements PetRepository {
    private final List<PetDTO> petDTOList;

    public PetRepositoryImpl() {
        this.petDTOList = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        DomesticPetDTO domesticPetDTO = new DomesticPetDTO();
        domesticPetDTO.setId(1);
        domesticPetDTO.setName("Max");
        domesticPetDTO.setGender(M);
        domesticPetDTO.setPetType(DOG);
        domesticPetDTO.setBirthDate(LocalDate.of(2018, 7, 26));

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(1);
        ownerDTO.setFirstName("John");
        ownerDTO.setLastName("Doe");
        ownerDTO.setGender(M);
        ownerDTO.setCity("Hyderabad");
        ownerDTO.setState("Andhra Pradesh");
        ownerDTO.setMobileNumber("9009009001");
        ownerDTO.setEmailId("john.doe@abhishekvermaa10.com");

        domesticPetDTO.setOwnerDTO(ownerDTO);
        petDTOList.add(domesticPetDTO);

        WildPetDTO wildPetDTO = new WildPetDTO();
        wildPetDTO.setId(2);
        wildPetDTO.setName("Fluffy");
        wildPetDTO.setGender(F);
        wildPetDTO.setPetType(CAT);
        wildPetDTO.setBirthPlace("Jim Corbett National Park");

        ownerDTO = new OwnerDTO();
        ownerDTO.setId(2);
        ownerDTO.setFirstName("Jane");
        ownerDTO.setLastName("Smith");
        ownerDTO.setGender(F);
        ownerDTO.setCity("Visakhapatnam");
        ownerDTO.setState("Andhra Pradesh");
        ownerDTO.setMobileNumber("9009009002");
        ownerDTO.setEmailId("jane.smith@abhishekvermaa10.com");

        wildPetDTO.setOwnerDTO(ownerDTO);
        petDTOList.add(wildPetDTO);


    }

    @Override
    public Optional<PetDTO> findById(int petId) {
        return petDTOList.stream().filter(pet -> pet.getId() == petId).findFirst();

    }
}
