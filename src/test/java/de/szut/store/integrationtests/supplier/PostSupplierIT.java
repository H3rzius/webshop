package de.szut.store.integrationtests.supplier;

import de.szut.store.testcontainers.AbstractIntegrationTest;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PostSupplierIT extends AbstractIntegrationTest {


    @Test
    @Transactional
    void happyPath() throws Exception {
        String content = """
                {
                         "name": "Meier",
                         "street": "Benquestraße 50",
                         "postcode": "28209",
                         "city": "Bremen",
                         "phone": "01637122020"
                }
                """;

        // wir schicken ein POST an /store/supplier
        // wir setzen den ContentType auf application/json
        // Als Body schicken wir die Variable content
        // Das Ergebnis wird in contentAsString gespeichert
        final var contentAsString = this.mockMvc.perform(post("/store/supplier").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())  // Status soll 201 sein
                .andExpect(jsonPath("name", is("Meier")))   // es gibt eine Property name mit dem Wert Meier
                .andExpect(jsonPath("street", is("Benquestraße 50"))) // es gibt eine Property street mit dem Wert Benquestraße 50
                .andExpect(jsonPath("city", is("Bremen"))) // es gibt eine Property city mit dem Wert Bremen
                .andExpect(jsonPath("postcode", is("28209"))) // es gibt eine Property postcode mit dem Wert 28209
                .andExpect(jsonPath("phone", is("01637122020"))) //
                .andReturn()  // wir erhalten ein Result
                .getResponse()
                .getContentAsString();

        // wir lesen die id aus dem Result
        final var id = Long.parseLong(new JSONObject(contentAsString).get("sid").toString());

        // Ist das Objekt wirklich in der Datenbank?
        final var loadedEntity = supplierRepository.findById(id);
        assertThat(loadedEntity.get().getName()).isEqualTo("Meier");
        assertThat(loadedEntity.get().getContact().getStreet()).isEqualTo("Benquestraße 50");
        assertThat(loadedEntity.get().getContact().getPostcode()).isEqualTo("28209");
        assertThat(loadedEntity.get().getContact().getCity()).isEqualTo("Bremen");
        assertThat(loadedEntity.get().getContact().getPhone()).isEqualTo("01637122020");
    }

    @Test
    public void invalidPostcode() throws Exception {
        String content = """
                {
                         "name": "Meier",
                         "street": "Benquestraße 50",
                         "postcode": "2820",
                         "city": "Bremen",
                         "phone": "01637122020"
                }
                """;
        this.mockMvc.perform(post("/store/supplier").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}





