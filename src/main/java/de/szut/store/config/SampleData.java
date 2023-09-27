package de.szut.store.config;


import de.szut.store.article.ArticleEntity;
import de.szut.store.article.ArticleRepository;
import de.szut.store.contact.ContactEntity;
import de.szut.store.contact.ContactRepository;
import de.szut.store.supplier.SupplierEntity;
import de.szut.store.supplier.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SampleData {

    private final ArticleRepository articleRepository;
    private final SupplierRepository supplierRepository;

    private final ContactRepository contactRepository;


    public SampleData(ArticleRepository articleRepository, SupplierRepository supplierRepository, ContactRepository contactRepository) {
        this.articleRepository = articleRepository;
        this.supplierRepository = supplierRepository;
        this.contactRepository = contactRepository;

      createData();
    }


    @Transactional
    public void createData() {

        // create example supplier with articles
        var supplierEntity = new SupplierEntity();
        supplierEntity.setName("Stanley");
        var contact = new ContactEntity();
        contact.setCity("Berlin");
        contact.setStreet("Musterstraße 1");
        contact.setPostcode("12345");
        contact.setPhone("030123456");
        contact=this.contactRepository.save(contact);
        supplierEntity.setContact(contact);
        supplierEntity=this.supplierRepository.save(supplierEntity);
        var articleEntity = new ArticleEntity();
        articleEntity.setDesignation("Thermosflasche");
        articleEntity.setPrice(26.99);
        articleEntity.setSupplier(supplierEntity);
        articleEntity=this.articleRepository.save(articleEntity);
        var articleEntity2 = new ArticleEntity();
        articleEntity2.setDesignation("Wasserflasche");
        articleEntity2.setPrice(2.99);
        articleEntity2.setSupplier(supplierEntity);
        articleEntity2=this.articleRepository.save(articleEntity2);

        HashSet<ArticleEntity> articles = new HashSet<>();
        articles.add(articleEntity);
        articles.add(articleEntity2);

        supplierEntity.setArticles(articles);
        supplierEntity=this.supplierRepository.save(supplierEntity);
    }
}
