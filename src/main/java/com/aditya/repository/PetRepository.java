package com.aditya.repository;

import com.aditya.dto.OwnerDTO;
import com.aditya.dto.PetDTO;

import java.util.List;
import java.util.Optional;

public interface PetRepository {


    Optional<PetDTO> findById(int petId);


}
