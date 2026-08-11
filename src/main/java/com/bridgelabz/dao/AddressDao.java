package com.bridgelabz.dao;

import com.bridgelabz.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // UC-07 Add Address
    public Address saveAddress(Address address) {

        String sql =
                "INSERT INTO address " +
                        "(contact_id, street, city, state, pincode) " +
                        "VALUES (?, ?, ?, ?, ?) " +
                        "RETURNING id";

        Integer id = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                address.getContactId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPincode()
        );

        address.setId(id);

        return address;
    }


    // UC-08 Get Address
    public List<Address> getAddressesByContactId(int contactId) {

        String sql =
                "SELECT * FROM address " +
                        "WHERE contact_id = ? " +
                        "ORDER BY id";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Address address = new Address();

                    address.setId(rs.getInt("id"));
                    address.setContactId(
                            rs.getInt("contact_id")
                    );
                    address.setStreet(
                            rs.getString("street")
                    );
                    address.setCity(
                            rs.getString("city")
                    );
                    address.setState(
                            rs.getString("state")
                    );
                    address.setPincode(
                            rs.getString("pincode")
                    );

                    return address;
                },
                contactId
        );
    }


    // UC-09 Update Address
    public boolean updateAddress(
            int contactId,
            Address address) {

        String sql =
                "UPDATE address " +
                        "SET street = ?, city = ?, " +
                        "state = ?, pincode = ? " +
                        "WHERE id = ? AND contact_id = ?";

        int rows = jdbcTemplate.update(
                sql,
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPincode(),
                address.getId(),
                contactId
        );

        return rows > 0;
    }


    // UC-10 Delete Address
    public boolean deleteAddress(
            int contactId,
            int addressId) {

        String sql =
                "DELETE FROM address " +
                        "WHERE id = ? AND contact_id = ?";

        int rows = jdbcTemplate.update(
                sql,
                addressId,
                contactId
        );

        return rows > 0;
    }
}