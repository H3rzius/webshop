package de.szut.store.mapping;


import de.szut.store.article.*;
import de.szut.store.article.GetAllArticlesBySupplierDto;
import de.szut.store.article.GetArticleDto;
import de.szut.store.contact.ContactEntity;
import de.szut.store.supplier.AddSupplierDto;
import de.szut.store.supplier.GetSupplierDto;
import de.szut.store.supplier.SupplierEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class MappingService {

    public SupplierEntity mapAddSupplierDtoToSupplierEntity(AddSupplierDto dto) {
        var supplierEntity = new SupplierEntity();
        supplierEntity.setName(dto.getName());
        var contact= new ContactEntity();
        contact.setCity(dto.getCity());
        contact.setStreet(dto.getStreet());
        contact.setPostcode(dto.getPostcode());
        contact.setPhone(dto.getPhone());
        supplierEntity.setContact(contact);
        return supplierEntity;
    }

    public GetSupplierDto mapSupplierEntityToGetSupplierDto(SupplierEntity supplierEntity) {
        var getSupplierDto = new GetSupplierDto();
        getSupplierDto.setSid(supplierEntity.getSid());
        getSupplierDto.setName(supplierEntity.getName());
        getSupplierDto.setStreet(supplierEntity.getContact().getStreet());
        getSupplierDto.setPostcode(supplierEntity.getContact().getPostcode());
        getSupplierDto.setCity(supplierEntity.getContact().getCity());
        getSupplierDto.setPhone(supplierEntity.getContact().getPhone());
        return getSupplierDto;
    }

    public GetAllArticlesBySupplierDto mapSupplierEntityToGetAllArticlesBySupplierDto(SupplierEntity supplierEntity) {
        var getAllArticlesBySupplierDto = new GetAllArticlesBySupplierDto();
        getAllArticlesBySupplierDto.setSupplierId(supplierEntity.getSid());
        getAllArticlesBySupplierDto.setName(supplierEntity.getName());

        // extract articles from supplierEntity and map them to GetArticleDto

//        var articles = new HashSet<GetArticleDto>();
//        for(var articleEntity : supplierEntity.getArticles()) {
//            var getArticleDto = new GetArticleDto();
//            getArticleDto.setAid(articleEntity.getAid());
//            getArticleDto.setPrice(articleEntity.getPrice());
//            getArticleDto.setDesignation(articleEntity.getDesignation());
//            articles.add(getArticleDto);
//        }
//        getAllArticlesBySupplierDto.setArticles(articles);

        // use stream api to map articles to GetArticleDto
        getAllArticlesBySupplierDto.setArticles(supplierEntity.getArticles().stream().map(articleEntity -> {
            var getArticleDto = new GetArticleDto();
            getArticleDto.setAid(articleEntity.getAid());
            getArticleDto.setPrice(articleEntity.getPrice());
            getArticleDto.setDesignation(articleEntity.getDesignation());
            return getArticleDto;
        }).collect(Collectors.toSet()));

        return getAllArticlesBySupplierDto;
    }

    public GetArticleDto mapArticleEntityToGetArticleDto(ArticleEntity articleEntity) {
        var getArticleDto = new GetArticleDto();
        getArticleDto.setAid(articleEntity.getAid());
        getArticleDto.setPrice(articleEntity.getPrice());
        getArticleDto.setDesignation(articleEntity.getDesignation());
        return getArticleDto;
    }

    public ArticleEntity mapAddArticleDtoToArticleEntity(AddArticleDto dto) {
        var articleEntity = new ArticleEntity();
        articleEntity.setDesignation(dto.getDesignation());
        articleEntity.setPrice(dto.getPrice());
        return articleEntity;
    }


}
