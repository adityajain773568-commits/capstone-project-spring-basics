package com.aditya.util;

import com.aditya.dto.DomesticPetDTO;
import com.aditya.dto.OwnerDTO;
import com.aditya.dto.PetDTO;
import com.aditya.dto.WildPetDTO;
import com.aditya.enums.Gender;
import com.aditya.enums.PetType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class InputUtil {

    private InputUtil(){

    }

    public static int acceptMenuOption(Scanner scanner) {
        System.out.println("Press 1 to add new owner.");
        System.out.println("Press 2 to fetch owner details.");
        System.out.println("Press 3 to update pet details of owner.");
        System.out.println("Press 4 to delete owner details.");
        System.out.println("Press 5 to all owners.");
        System.out.println("Press 6 to fetch pet details.");

        int menuOption = scanner.nextInt();
        if (1 <= menuOption && menuOption <= 6) {
            return menuOption;
        } else {
            System.out.println("Invalid Option entered");
            return acceptMenuOption(scanner);
        }
    }

    public static boolean wantToContinue(Scanner scanner) {
        System.out.println("Print Y to continue and N to exit.");
        char choice = scanner.next().toUpperCase().charAt(0);
        return 'Y' == choice;
    }

    public static OwnerDTO acceptOwnerDetailsToSave(Scanner scanner) {
        System.out.println("Enter id of owner : ");
        int id = scanner.nextInt();
        System.out.println("Enter first name of owner : ");
        String firstName = scanner.next();
        System.out.println("Enter last name of owner : ");
        String lastName = scanner.next();
        System.out.println("Enter gender of owner from choices  " + Arrays.asList(Gender.values()));
        String gender = scanner.next().toUpperCase();
        System.out.println("Enter city of owner : ");
        String city = scanner.next();
        System.out.println("Enter state of owner : ");
        String state = scanner.next();
        System.out.println("Enter mobile number of owner : ");
        String mobileNumber = scanner.next();
        System.out.println("Enter emailId of owner:");
        String emailId = scanner.next();
        try {
            OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setId(id);
            ownerDTO.setFirstName(firstName);
            ownerDTO.setLastName(lastName);
            ownerDTO.setGender(Gender.valueOf(gender));
            ownerDTO.setCity(city);
            ownerDTO.setState(state);
            ownerDTO.setMobileNumber(mobileNumber);
            ownerDTO.setEmailId(emailId);
            return ownerDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return acceptOwnerDetailsToSave(scanner);
        }

    }

    public static PetDTO acceptPetDetailsToSave(Scanner scanner) {
        System.out.println("Enter id of pet : ");
        int petId = scanner.nextInt();
        System.out.println("Enter name of pet : ");
        String petName = scanner.next();
        System.out.println("Press D for Domestic Pet and W for Wild Pet.");
        char choice = scanner.next().toUpperCase().charAt(0);
        String petPlaceOfBirth = null;
        LocalDate petDateOfBirth = null;
        if (choice == 'D') {
            System.out.println("Enter date of birth of pet in DD-MM-YYYY format : ");
            petDateOfBirth = convertStringToLocalDate(scanner.next());
        } else {
            System.out.println("Enter place of birth of pet : ");
            petPlaceOfBirth = scanner.next();
        }
        System.out.println("Enter gender of pet : " + Arrays.asList(Gender.values()));
        String gender = scanner.next();
        System.out.println("Enter pet type : " + Arrays.asList(PetType.values()));
        String petType = scanner.next().toUpperCase();
        try {
            PetDTO petDTO = null;
            if (choice == 'D') {
                petDTO = new DomesticPetDTO();
                ((DomesticPetDTO) petDTO).setBirthDate(petDateOfBirth);
            } else if (choice == 'W') {
                petDTO = new WildPetDTO();
                ((WildPetDTO) petDTO).setBirthPlace(petPlaceOfBirth);
            } else {
                throw new IllegalAccessException("unsupported pet choice : " + choice);
            }
            petDTO.setId(petId);
            petDTO.setName(petName);
            petDTO.setGender(Gender.valueOf(gender));
            petDTO.setPetType(PetType.valueOf(petType));
            return petDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return acceptPetDetailsToSave(scanner);
        }

    }

    public static String acceptPetDetailsToUpdate(Scanner scanner) {
        System.out.println("Enter updated name of pet : ");
        return scanner.next();
    }

    public static int acceptOwnerIdToOperate(Scanner scanner) {
        System.out.println("Enter id of owner : ");
        return scanner.nextInt();
    }

    public static int acceptPetIdToOperate(Scanner scanner) {
        System.out.println("Enter id of pet : ");
        return scanner.nextInt();
    }


    public static LocalDate convertStringToLocalDate(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dob, formatter);
    }

}
