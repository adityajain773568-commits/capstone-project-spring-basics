package com.aditya.repository.impl;

import com.aditya.dto.DomesticPetDTO;
import com.aditya.dto.PetDTO;

import static org.junit.jupiter.api.Assertions.*;

import com.aditya.exception.PetNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.aspectj.util.Reflection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = PetRepositoryImpl.class)
@TestPropertySource(value = "classpath:messages.properties")
class PetRepositoryImplTest {

    @Autowired
    private PetRepositoryImpl petRepository;

    private List<PetDTO> petDTOList;

    @BeforeEach()
    public void setUp(){
        petDTOList = (List<PetDTO>) ReflectionTestUtils.getField(petRepository, "petDTOList");
        petDTOList.clear();
    }


    @Test
    void testFindByIdWhenFound() {
        DomesticPetDTO domesticPetDTO = new DomesticPetDTO();
        Optional<PetDTO> expectedOptionalPetDTO = Optional.of(domesticPetDTO);
        expectedOptionalPetDTO.get().setId(1);
        petDTOList.add(expectedOptionalPetDTO.get());
        Optional<PetDTO> actualOptionalPetDTO = petRepository.findById(1);
        assertEquals(expectedOptionalPetDTO,actualOptionalPetDTO);
    }
    @Test
    void testFindByIdWhenNotFound() {
        Optional<PetDTO> expectedOptionalPetDTO = Optional.empty();
        Optional<PetDTO> actualOptionalPetDTO = petRepository.findById(2);
        assertEquals(expectedOptionalPetDTO, actualOptionalPetDTO);
    }
}