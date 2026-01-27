package com.aditya.repository;

import com.aditya.dto.OwnerDTO;
import com.aditya.exception.DuplicateOwnerIdException;
import com.aditya.exception.OwnerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository {

    void save(OwnerDTO ownerDTO);

    Optional<OwnerDTO> findById(int ownerId);

    void updatePetDetails(int ownerId, String petName) ;

    void deleteById(int ownerId);

    List<OwnerDTO> findAll();
}
