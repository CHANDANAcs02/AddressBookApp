package com.bridgelabz.controller;

import com.bridgelabz.model.Address;
import com.bridgelabz.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts/{contactId}/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    // UC-07 Add Address
    @PostMapping
    public ResponseEntity<?> addAddress(
            @PathVariable int contactId,
            @RequestBody Address address) {

        address.setContactId(contactId);

        Address savedAddress =
                addressService.saveAddress(address);

        if (savedAddress == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Contact not found");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAddress);
    }


    // UC-08 Get Address
    @GetMapping
    public List<Address> getAddresses(
            @PathVariable int contactId) {

        return addressService
                .getAddressesByContactId(contactId);
    }


    // UC-09 Update Address
    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(
            @PathVariable int contactId,
            @PathVariable int addressId,
            @RequestBody Address address) {

        address.setId(addressId);
        address.setContactId(contactId);

        boolean updated =
                addressService.updateAddress(
                        contactId,
                        address
                );

        if (!updated) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Address not found");
        }

        return ResponseEntity.ok(
                "Address updated successfully"
        );
    }


    // UC-10 Delete Address
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(
            @PathVariable int contactId,
            @PathVariable int addressId) {

        boolean deleted =
                addressService.deleteAddress(
                        contactId,
                        addressId
                );

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Address not found");
        }

        return ResponseEntity.ok(
                "Address deleted successfully"
        );
    }
}