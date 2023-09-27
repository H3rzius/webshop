package de.szut.store.article;

import lombok.Data;

import java.util.Set;


@Data
public class GetAllArticlesBySupplierDto {


    private Long supplierId;

    private String name;

    private Set<GetArticleDto> articles;
}

