package com.aditya.service;

import com.aditya.dto.OwnerDTO;
import com.aditya.exception.DuplicateOwnerIdException;
import com.aditya.exception.OwnerNotFoundException;

import java.util.List;

public interface OwnerService {

    void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerIdException;

    OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

    void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

    void deleteOwner(int ownerId) throws OwnerNotFoundException;

    List<OwnerDTO> findAllOwners();
}
