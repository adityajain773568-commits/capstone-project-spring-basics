package com.aditya.service.impl;

import com.aditya.dto.OwnerDTO;
import com.aditya.dto.PetDTO;
import com.aditya.exception.DuplicateOwnerIdException;
import com.aditya.exception.OwnerNotFoundException;
import com.aditya.repository.OwnerRepository;
import com.aditya.service.OwnerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final String ownerAlreadyExists;
    private final String ownerNotFound;

    public OwnerServiceImpl(@Value("${owner.not.found}") String ownerNotFound, OwnerRepository ownerRepository, @Value("${owner.already.exists}") String ownerAlreadyExists) {
        this.ownerNotFound = ownerNotFound;
        this.ownerRepository = ownerRepository;
        this.ownerAlreadyExists = ownerAlreadyExists;
    }

    @Override
    public void saveOwner(OwnerDTO ownerDTO) {
        int id = ownerDTO.getId();
        if (ownerRepository.findById(id).isPresent()) {
            throw new DuplicateOwnerIdException(String.format(ownerAlreadyExists, id));
        } else {
            ownerRepository.save(ownerDTO);
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {
        ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
        ownerRepository.updatePetDetails(ownerId, petName);
    }

    @Override
    public void deleteOwner(int ownerId) {
        ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        return ownerRepository.findAll();
    }
}
