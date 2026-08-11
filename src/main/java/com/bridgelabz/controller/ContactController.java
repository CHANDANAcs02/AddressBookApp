package com.bridgelabz.controller;

import com.bridgelabz.model.Contact;
import com.bridgelabz.model.ContactDetails;
import com.bridgelabz.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;


    // UC-01 Add Contact
    @PostMapping
    public ResponseEntity<?> addContact(
            @RequestBody Contact contact) {

        if (contact.getName() == null ||
                contact.getName().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Contact name is mandatory");
        }

        if (contact.getPhone() == null ||
                contact.getPhone().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Phone number is mandatory");
        }

        if (contact.getEmail() == null ||
                contact.getEmail().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Email is mandatory");
        }

        if (!isValidEmail(contact.getEmail())) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid email");
        }

        Contact savedContact =
                contactService.saveContact(contact);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedContact);
    }


    // UC-02 Get Contact
    @GetMapping("/{id}")
    public ResponseEntity<?> getContact(
            @PathVariable int id) {

        Contact contact =
                contactService.getContact(id);

        if (contact == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Contact not found");
        }

        return ResponseEntity.ok(contact);
    }


    // UC-03 Get All Contacts
    @GetMapping
    public List<Contact> getAllContacts() {

        return contactService.getAllContacts();
    }


    // UC-04 Update Contact
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @PathVariable int id,
            @RequestBody Contact contact) {

        if (contact.getName() == null ||
                contact.getName().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Contact name is mandatory");
        }

        if (contact.getPhone() == null ||
                contact.getPhone().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Phone number is mandatory");
        }

        if (contact.getEmail() == null ||
                contact.getEmail().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Email is mandatory");
        }

        if (!isValidEmail(contact.getEmail())) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid email");
        }

        boolean updated =
                contactService.updateContact(id, contact);

        if (!updated) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Contact not found");
        }

        return ResponseEntity.ok(
                "Contact updated successfully"
        );
    }


    // UC-05 Delete Contact
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(
            @PathVariable int id) {

        boolean deleted =
                contactService.deleteContact(id);

        if (!deleted) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Contact not found");
        }

        return ResponseEntity.ok(
                "Contact deleted successfully"
        );
    }


    // UC-06 Search Contact
    @GetMapping("/search")
    public List<Contact> searchContacts(
            @RequestParam String name) {

        return contactService.searchContacts(name);
    }


    // Email validation
    private boolean isValidEmail(String email) {

        String emailPattern =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return Pattern
                .matches(emailPattern, email);
    }
    // UC-11 View Complete Contact Details
    @GetMapping("/{id}/details")
    public ResponseEntity<?> getContactDetails(
            @PathVariable int id) {

        ContactDetails contactDetails =
                contactService.getContactDetails(id);

        if (contactDetails == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Contact not found");
        }

        return ResponseEntity.ok(contactDetails);
    }
}