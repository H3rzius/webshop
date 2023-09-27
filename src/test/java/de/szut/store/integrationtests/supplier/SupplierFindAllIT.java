package de.szut.store.integrationtests.supplier;
import de.szut.store.contact.ContactEntity;
import de.szut.store.supplier.SupplierEntity;
import de.szut.store.testcontainers.AbstractIntegrationTest;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SupplierFindAllIT extends AbstractIntegrationTest {

    @Test
    public void findAll() throws Exception {

        var supplier=new SupplierEntity();
        supplier.setName("Meier");
        ContactEntity contact=new ContactEntity();
        contact.setCity("Bremen");
        contact.setStreet("Benquestraße 50");
        contact.setPostcode("28209");
        contact.setPhone("01637122020");
        supplier.setContact(contact);

        supplier=supplierRepository.save(supplier);

        this.mockMvc.perform(get("/store/supplier"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Meier")))
                .andExpect(jsonPath("$[0].street", is("Benquestraße 50")))
                .andExpect(jsonPath("$[0].postcode", is("28209")))
                .andExpect(jsonPath("$[0].city", is("Bremen")))
                .andExpect(jsonPath("$[0].phone", is("01637122020")));

    }
}
