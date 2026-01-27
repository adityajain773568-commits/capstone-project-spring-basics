package com.aditya.service.impl;

import com.aditya.dto.PetDTO;
import com.aditya.exception.PetNotFoundException;
import com.aditya.repository.PetRepository;
import com.aditya.service.PetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final String petNotFound;

    public PetServiceImpl(PetRepository petRepository, @Value("${pet.not.found}") String petNotFound) {
        this.petRepository = petRepository;
        this.petNotFound = petNotFound;
    }

    @Override
    public PetDTO findPet(int petId) {
         return petRepository.findById(petId).orElseThrow(()->new PetNotFoundException(String.format(petNotFound, petId)));
    }
}
