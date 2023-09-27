package de.szut.store.article;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddArticleDto {

    //  designation und price

    @NotBlank
    @Size(max = 255)
    private String designation;

    @NotBlank
    @Min(0)
    private Double price;
}
