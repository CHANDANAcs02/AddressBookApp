# Project 2: Address Book App

## Project Overview

The Address Book App allows users to manage contact information and associated addresses.

## Features

### Contact Management

- Add contacts
- View contacts
- View all contacts
- Update contacts
- Delete contacts
- Search contacts

### Address Management

- Add addresses
- View addresses
- Update addresses
- Delete addresses
- View complete contact details

## API Endpoints

### Contact APIs

| Use Case | Endpoint | Method |
|---|---|---|
| Add Contact | `/contacts` | POST |
| Get Contact | `/contacts/{id}` | GET |
| Get All Contacts | `/contacts` | GET |
| Update Contact | `/contacts/{id}` | PUT |
| Delete Contact | `/contacts/{id}` | DELETE |
| Search Contact | `/contacts/search?name={name}` | GET |

### Address APIs

| Use Case | Endpoint | Method |
|---|---|---|
| Add Address | `/contacts/{id}/address` | POST |
| Get Address | `/contacts/{id}/address` | GET |
| Update Address | `/contacts/{id}/address` | PUT |
| Delete Address | `/contacts/{id}/address` | DELETE |
| View Contact Details | `/contacts/{id}/details` | GET |

## Contact Information

- Contact ID
- Name
- Phone
- Email

## Address Information

- Address ID
- Contact ID
- Street
- City
- State
- Pincode

## Business Rules

1. Contact name is mandatory.
2. Phone number is mandatory.
3. Email should be valid.
4. Every contact must have a unique ID.
5. An address must belong to an existing contact.
6. A contact can have one or more addresses.
7. Deleting a contact should handle its associated addresses appropriately.
8. Searching should support partial name matching.
