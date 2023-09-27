package de.szut.store.contact;


import de.szut.store.supplier.SupplierEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="Contact")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @NotBlank(message = "Street is mandatory")
    @Size(max = 50, message = "Street must be less than 50 characters")
    private String street;

    @Column(name="zip")
    @NotBlank(message = "Postcode is mandatory")
    @Size(min=5, max=5, message = "Postcode must be 5 characters long")
    private String postcode;

    @NotBlank(message = "City is mandatory")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    private String phone;

    @OneToOne(mappedBy = "contact", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private SupplierEntity supplier;

}
