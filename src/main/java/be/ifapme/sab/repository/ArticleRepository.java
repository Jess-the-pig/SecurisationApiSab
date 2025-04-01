package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
