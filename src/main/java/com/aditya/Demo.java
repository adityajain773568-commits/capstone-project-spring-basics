package com.aditya;

import com.aditya.dto.OwnerDTO;
import com.aditya.dto.PetDTO;
import com.aditya.service.OwnerService;
import com.aditya.service.PetService;
import com.aditya.util.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@PropertySource(value = "classpath:messages.properties")
public class Demo implements CommandLineRunner {
    private final OwnerService ownerService;
    private final PetService petService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public Demo( OwnerService ownerService,  PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext container = SpringApplication.run(Demo.class, args);
        Service service = container.getBean(Service.class);

    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Welcome To Petistan");
                switch (InputUtil.acceptMenuOption(scanner)) {
                    case 1:
                        OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailsToSave(scanner);
                        PetDTO petDTO = InputUtil.acceptPetDetailsToSave(scanner);
                        ownerDTO.setPetDTO(petDTO);
                        ownerService.saveOwner(ownerDTO);
                        System.out.println("Owner has been Saved successfully!");
                        break;
                    case 2:
                        System.out.println("Enter owner id to fetch details.");
                        int ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        OwnerDTO owner = ownerService.findOwner(ownerId);
                        System.out.println("Owner has been fetched successfully! ");
                        System.out.println("Owner details are : " + owner.toString());
                        break;
                    case 3:
                        System.out.println("Enter owner id to operate : ");
                        ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        System.out.println("Enter updated pet name : ");
                        String petName = InputUtil.acceptPetDetailsToUpdate(scanner);
                        ownerService.updatePetDetails(ownerId, petName);
                        System.out.println("Pet details updated successfully!");
                        break;
                    case 4:
                        System.out.println("Enter owner id to delete record : ");
                        ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        ownerService.deleteOwner(ownerId);
                        System.out.println("Owner has been deleted successfully!");
                        break;
                    case 5:
                        List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
                        System.out.println("Owner Details are : ");
                        ownerDTOList.forEach(ownerDTO1 -> System.out.println("First Name : " + ownerDTO1.getFirstName() + " || " + "Last Name : " + ownerDTO1.getLastName() + " || City : " + ownerDTO1.getCity() + " || email id : " + ownerDTO1.getEmailId() + " || pet details are : " + ownerDTO1.getPetDTO() + "  -->  pet name : " +ownerDTO1.getPetDTO().getName() + " || pet gender : " + ownerDTO1.getPetDTO().getPetType() + " || petType  : " + ownerDTO1.getPetDTO().getPetType() ));
                        break;
                    case 6:
                        System.out.println("Enter pet id  to fetch pet details : ");
                        int petId = InputUtil.acceptPetIdToOperate(scanner);
                        petDTO = petService.findPet(petId);
                        System.out.println(" Pet details fetched successfully!");
                        System.out.println(petDTO);
                        break;
                    default:
                        System.out.println("Select valid choice !!! ");
                        break;
                }

            } while (InputUtil.wantToContinue(scanner));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
