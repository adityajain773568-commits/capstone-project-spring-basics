package com.aditya.service;

import com.aditya.dto.PetDTO;
import com.aditya.exception.PetNotFoundException;

public interface PetService {
    PetDTO findPet(int petId) throws PetNotFoundException;
}
