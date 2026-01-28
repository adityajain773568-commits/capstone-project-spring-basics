package com.aditya.service.impl;

import com.aditya.dto.DomesticPetDTO;
import com.aditya.dto.PetDTO;
import com.aditya.exception.PetNotFoundException;
import com.aditya.repository.impl.PetRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

@SpringBootTest(classes = PetServiceImpl.class)
@TestPropertySource(value = "classpath:messages.properties")
class PetServiceImplTest {

    @Autowired
    private PetServiceImpl petService;

    @MockitoBean
    private PetRepositoryImpl petRepository;

    @Test
    void testFindPetWhenFound() {
        DomesticPetDTO expectedPetDTO = new DomesticPetDTO();
        Optional<PetDTO> optionalPetDTO = Optional.of(expectedPetDTO);
        when(petRepository.findById(1)).thenReturn(optionalPetDTO);
        PetDTO actualPetDTO = petService.findPet(1);
        assertEquals(expectedPetDTO, actualPetDTO);
        verify(petRepository,times(1)).findById(1);
    }

    @Test
    void testFindPetWhenNotFound() {
        String expectedMessage = "Pet not found with petId = 2";
        Optional<PetDTO> optionalPetDTO = Optional.empty();
        when(petRepository.findById(2)).thenReturn(optionalPetDTO);
        PetNotFoundException actualException = assertThrows(PetNotFoundException.class, () -> petService.findPet(2));
        assertEquals(expectedMessage,actualException.getMessage());
        verify(petRepository , times(1)).findById(2);
    }
}