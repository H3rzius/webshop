package de.szut.store.supplier;

import de.szut.store.contact.ContactEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public String createSupplier(@RequestBody AddSupplierDto dto) {
        System.out.println(dto);
        // convert dto to entity
        var supplierEntity = new SupplierEntity();
        supplierEntity.setName(dto.getName());
        var contact= new ContactEntity();
        contact.setCity(dto.getCity());
        contact.setStreet(dto.getStreet());
        contact.setPostcode(dto.getPostcode());
        contact.setPhone(dto.getPhone());
        supplierEntity.setContact(contact);
       supplierService.createSupplier(supplierEntity);
       return "Supplier created";
    }
}
