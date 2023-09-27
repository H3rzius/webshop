package de.szut.store.article;

import de.szut.store.mapping.MappingService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/store/article")
public class ArticleController {

    private final ArticleService articleService;

    private final MappingService mappingService;

    public ArticleController(ArticleService articleService, MappingService mappingService   ) {
        this.articleService = articleService;
        this.mappingService = mappingService;
    }

    @GetMapping(
    )
    public Collection<GetArticleDto> getArticles(@RequestParam(required = false) final String designation) {
        if(designation!=null) {
            var articleEntityList = this.articleService.getArticleByDesignation(designation);
            return articleEntityList.stream().map(articleEntity -> {
                return this.mappingService.mapArticleEntityToGetArticleDto(articleEntity);
            }).toList();
        }
        var articles = this.articleService.getArticles();
        return articles.stream().map(articleEntity -> {
            return this.mappingService.mapArticleEntityToGetArticleDto(articleEntity);
        }).toList();
    }

    @GetMapping("/{id}")
    public GetArticleDto getArticleById(@PathVariable("id") final Long id) {


        var articleEntity = this.articleService.get(id);
        return this.mappingService.mapArticleEntityToGetArticleDto(articleEntity);
    }


    @PostMapping()
    public GetArticleDto createArticle(@RequestBody final AddArticleDto dto) {
        var articleEntity = this.mappingService.mapAddArticleDtoToArticleEntity(dto);
        var savedArticle = this.articleService.createArticle(articleEntity);
        return this.mappingService.mapArticleEntityToGetArticleDto(savedArticle);
    }

}
