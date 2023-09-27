package de.szut.store.article;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Collection<ArticleEntity> getArticles() {
        // darf der das?
        // enterne aus dem Ergeniss die Artikel die nicht mehr lieferbar sind
        return articleRepository.findAll();
    }

    public ArticleEntity get(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    public Collection<ArticleEntity>  getArticleByDesignation(String designation) {
        return articleRepository.findByDesignation(designation);

    }

    public ArticleEntity createArticle(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }
}
