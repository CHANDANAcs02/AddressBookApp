package com.bridgelabz.service;

import com.bridgelabz.dao.ContactDao;
import com.bridgelabz.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.dao.AddressDao;
import com.bridgelabz.model.Address;
import com.bridgelabz.model.ContactDetails;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private AddressDao addressDao;


    // CREATE
    public Contact saveContact(Contact contact) {
        return contactDao.saveContact(contact);
    }


    // GET ONE
    public Contact getContact(int id) {
        return contactDao.getContact(id);
    }


    // GET ALL
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }


    // UPDATE
    public boolean updateContact(int id, Contact contact) {
        return contactDao.updateContact(id, contact);
    }


    // DELETE
    public boolean deleteContact(int id) {
        return contactDao.deleteContact(id);
    }


    // SEARCH
    public List<Contact> searchContacts(String name) {
        return contactDao.searchContacts(name);
    }
    //View all
    public ContactDetails getContactDetails(int id) {

        Contact contact = contactDao.getContact(id);

        if (contact == null) {
            return null;
        }

        List<Address> addresses =
                addressDao.getAddressesByContactId(id);

        return new ContactDetails(
                contact.getId(),
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                addresses
        );
    }
}