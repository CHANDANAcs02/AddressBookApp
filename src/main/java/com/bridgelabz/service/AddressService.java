package com.bridgelabz.service;

import com.bridgelabz.dao.AddressDao;
import com.bridgelabz.dao.ContactDao;
import com.bridgelabz.model.Address;
import com.bridgelabz.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ContactDao contactDao;


    // UC-07 Add Address
    public Address saveAddress(Address address) {

        Contact contact =
                contactDao.getContact(address.getContactId());

        if (contact == null) {
            return null;
        }

        return addressDao.saveAddress(address);
    }


    // UC-08 Get Address
    public List<Address> getAddressesByContactId(
            int contactId) {

        return addressDao.getAddressesByContactId(contactId);
    }


    // UC-09 Update Address
    public boolean updateAddress(
            int contactId,
            Address address) {

        return addressDao.updateAddress(
                contactId,
                address
        );
    }


    // UC-10 Delete Address
    public boolean deleteAddress(
            int contactId,
            int addressId) {

        return addressDao.deleteAddress(
                contactId,
                addressId
        );
    }
}