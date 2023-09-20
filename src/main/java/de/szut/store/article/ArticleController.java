package de.szut.store.article;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/articles")
    public Collection<ArticleEntity> getArticles() {
        return articleService.getArticles();
    }
}
