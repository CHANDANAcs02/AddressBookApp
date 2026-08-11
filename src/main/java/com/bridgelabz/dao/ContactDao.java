package com.bridgelabz.dao;

import com.bridgelabz.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // CREATE
    public Contact saveContact(Contact contact) {

        String sql =
                "INSERT INTO contact (name, phone, email) " +
                        "VALUES (?, ?, ?) " +
                        "RETURNING id";

        Integer id = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                contact.getName(),
                contact.getPhone(),
                contact.getEmail()
        );

        contact.setId(id);

        return contact;
    }


    // GET ONE
    public Contact getContact(int id) {

        String sql =
                "SELECT * FROM contact WHERE id = ?";

        List<Contact> contacts = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Contact contact = new Contact();

                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));
                    contact.setPhone(rs.getString("phone"));
                    contact.setEmail(rs.getString("email"));

                    return contact;
                },
                id
        );

        if (contacts.isEmpty()) {
            return null;
        }

        return contacts.get(0);
    }


    // GET ALL
    public List<Contact> getAllContacts() {

        String sql =
                "SELECT * FROM contact ORDER BY id";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Contact contact = new Contact();

                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));
                    contact.setPhone(rs.getString("phone"));
                    contact.setEmail(rs.getString("email"));

                    return contact;
                }
        );
    }


    // UPDATE
    public boolean updateContact(
            int id,
            Contact contact) {

        String sql =
                "UPDATE contact " +
                        "SET name = ?, phone = ?, email = ? " +
                        "WHERE id = ?";

        int rows = jdbcTemplate.update(
                sql,
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                id
        );

        return rows > 0;
    }


    // DELETE
    public boolean deleteContact(int id) {

        String sql =
                "DELETE FROM contact WHERE id = ?";

        int rows = jdbcTemplate.update(sql, id);

        return rows > 0;
    }


    // SEARCH BY NAME
    public List<Contact> searchContacts(String name) {

        String sql =
                "SELECT * FROM contact " +
                        "WHERE name ILIKE ? " +
                        "ORDER BY id";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Contact contact = new Contact();

                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));
                    contact.setPhone(rs.getString("phone"));
                    contact.setEmail(rs.getString("email"));

                    return contact;
                },
                "%" + name + "%"
        );
    }
}