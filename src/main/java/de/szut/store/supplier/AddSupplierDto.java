package de.szut.store.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddSupplierDto {

    // name (not blank), maxsize =50), street, postcode, city, phone

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @Size(max = 50, message = "Name must be less than 50 characters")
    private String street;

    @NotBlank(message = "Postcode is mandatory")
    @Size(min=5, max=5, message = "Postcode must be 5 characters long")
    private String postcode;

    @NotBlank(message = "City is mandatory")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    private String phone;

}


// A Json to create a new supplier:

/*
{
    "name": "Supplier 1",
    "street": "Street 1",
    "postcode": "12345",
    "city": "City 1",
    "phone": "123456789"
}
 */


