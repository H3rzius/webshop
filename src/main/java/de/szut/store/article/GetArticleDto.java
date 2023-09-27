package de.szut.store.article;


import lombok.Data;

@Data
public class GetArticleDto {

    // Attribute aid, designation und price

    private Long aid;

    private String designation;

    private Double price;
}
