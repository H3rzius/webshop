package de.szut.store.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Collection<ArticleEntity> findByDesignation(String designation);
}
