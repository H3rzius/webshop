package de.szut.store.supplier;

import de.szut.store.article.ArticleEntity;
import de.szut.store.article.GetAllArticlesBySupplierDto;
import de.szut.store.contact.ContactEntity;
import de.szut.store.mapping.MappingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping ("/store/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    private final MappingService mappingService;

    public SupplierController(SupplierService supplierService, MappingService mappingService) {
        this.supplierService = supplierService;
        this.mappingService = mappingService;



    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public GetSupplierDto createSupplier(@Valid @RequestBody final AddSupplierDto dto) {
        // convert dto to entity
        var supplierEntity = mappingService.mapAddSupplierDtoToSupplierEntity(dto);
       final var savedSupplier=supplierService.createSupplier(supplierEntity);
       return mappingService.mapSupplierEntityToGetSupplierDto(savedSupplier);
    }

    @GetMapping
    public ResponseEntity<Set<GetSupplierDto>> findAllSuppliers() {
        var suppliers = new HashSet<GetSupplierDto>();
        for (var supplierEntity : supplierService.getSuppliers()) {
            suppliers.add(mappingService.mapSupplierEntityToGetSupplierDto(supplierEntity));
        }
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSupplierDto> findSupplierById(@PathVariable("id") final Long id) {
        var supplierEntity = supplierService.getSupplier(id);

        return new ResponseEntity<>(mappingService.mapSupplierEntityToGetSupplierDto(supplierEntity), HttpStatus.OK);
    }


    // get all articles by supplier
    @GetMapping("/{id}/articles")
    public ResponseEntity<GetAllArticlesBySupplierDto> getAllArticlesBySupplier(@PathVariable("id") final Long id) {
        var supplierEntity = supplierService.getSupplier(id);
        return new ResponseEntity<>(mappingService.mapSupplierEntityToGetAllArticlesBySupplierDto(supplierEntity), HttpStatus.OK);
    }
}
