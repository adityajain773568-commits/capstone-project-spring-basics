package com.aditya.service.impl;

import com.aditya.dto.DomesticPetDTO;
import com.aditya.dto.OwnerDTO;
import com.aditya.exception.DuplicateOwnerIdException;
import com.aditya.exception.OwnerNotFoundException;
import com.aditya.repository.impl.OwnerRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OwnerServiceImpl.class)
@TestPropertySource(value = "classpath:messages.properties")
class OwnerServiceImplTest {

    @MockitoBean
    private OwnerRepositoryImpl ownerRepository;
    @Autowired
    private OwnerServiceImpl ownerService;


    @Test
    void testSaveOwnerWhenNewOwnerId() {
        OwnerDTO expectedOwnerDTO = new OwnerDTO();
        expectedOwnerDTO.setId(1);
        Optional<OwnerDTO> optionalOwnerDTO = Optional.empty();
        when(ownerRepository.findById(1)).thenReturn(optionalOwnerDTO);
        ownerService.saveOwner(expectedOwnerDTO);
        verify(ownerRepository).findById(1);
        verify(ownerRepository).save(expectedOwnerDTO);

    }

    @Test
    void testSaveOwnerWhenExistingOwnerId() {
        String expectedMessage = "Owner with ownerId 2 already exists.";
        OwnerDTO expectedOwnerDTO = new OwnerDTO();
        expectedOwnerDTO.setId(2);
        Optional<OwnerDTO> optionalOwnerDTO = Optional.of(expectedOwnerDTO);
        when(ownerRepository.findById(2)).thenReturn(optionalOwnerDTO);
        DuplicateOwnerIdException exception = assertThrows(DuplicateOwnerIdException.class, () -> ownerService.saveOwner(expectedOwnerDTO));
        assertEquals(expectedMessage, exception.getMessage());
        verify(ownerRepository).findById(2);
    }

    @Test
    void testFindOwnerWhenOwnerFound() {
        OwnerDTO expectedOwnerDTO = new OwnerDTO();
        Optional<OwnerDTO> optionalOwnerDTO = Optional.of(expectedOwnerDTO);
        when(ownerRepository.findById(1)).thenReturn(optionalOwnerDTO);
        OwnerDTO actualOwnerDTO = ownerService.findOwner(1);
        assertEquals(expectedOwnerDTO, actualOwnerDTO);
        verify(ownerRepository, times(1)).findById(1);
    }

    @Test
    void testFindOwnerWhenOwnerNotFound() {
        String expectedMessage = "Owner not found with ownerId = 2";
        Optional<OwnerDTO> optionalOwnerDTO = Optional.empty();
        when(ownerRepository.findById(2)).thenReturn(optionalOwnerDTO);
        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class, () -> ownerService.findOwner(2));
        assertEquals(expectedMessage, exception.getMessage());
        verify(ownerRepository).findById(2);
    }

    @Test
    void testUpdatePetDetailsWhenFound() {
        OwnerDTO expectedOwnerDTO = new OwnerDTO();
        expectedOwnerDTO.setId(1);
        DomesticPetDTO domesticPetDTO = new DomesticPetDTO();
        domesticPetDTO.setId(1);
        domesticPetDTO.setName("Bhaskaran");
        expectedOwnerDTO.setPetDTO(domesticPetDTO);
        Optional<OwnerDTO> optionalOwnerDTO = Optional.of(expectedOwnerDTO);
        when(ownerRepository.findById(1)).thenReturn(optionalOwnerDTO);
        ownerService.updatePetDetails(1, "Akshat");
        assertEquals(expectedOwnerDTO.getPetDTO().getName(),optionalOwnerDTO.get().getPetDTO().getName() );
        verify(ownerRepository).findById(1);
        verify(ownerRepository).updatePetDetails(1, "Akshat");
    }

    @Test
    void testUpdatePetDetailsWhenNotFound() {
        String expectedMessage = "Owner not found with ownerId = 2";
        Optional<OwnerDTO> optionalOwnerDTO = Optional.empty();
        when(ownerRepository.findById(2)).thenReturn(optionalOwnerDTO);
        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class, () -> ownerService.updatePetDetails(2, "Akshat"));
        assertEquals(expectedMessage, exception.getMessage());
        verify(ownerRepository).findById(2);
    }

    @Test
    void testDeleteOwnerWhenFound() {
        OwnerDTO expectedOwnerDTO = new OwnerDTO();
        Optional<OwnerDTO> optionalOwnerDTO = Optional.of(expectedOwnerDTO);
        when(ownerRepository.findById(1)).thenReturn(optionalOwnerDTO);
        ownerService.deleteOwner(1);
        verify(ownerRepository).findById(1);
        verify(ownerRepository).deleteById(1);
    }
    @Test
    void testDeleteOwnerWhenNotFound() {
        String expectedMessage = "Owner not found with ownerId = 2";
        Optional<OwnerDTO> optionalOwnerDTO = Optional.empty();
        when(ownerRepository.findById(2)).thenReturn(optionalOwnerDTO);
        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class, () -> ownerService.deleteOwner(2));
        assertEquals(expectedMessage, exception.getMessage());
        verify(ownerRepository).findById(2);
    }

    @Test
    void findAllOwners() {
        List<OwnerDTO> expectedOwnerDTOList = new ArrayList<>();
        when(ownerRepository.findAll()).thenReturn(expectedOwnerDTOList);
        List<OwnerDTO> actualOwnerDTOList = ownerService.findAllOwners();
        assertNotNull(actualOwnerDTOList);
        verify(ownerRepository).findAll();
    }
}