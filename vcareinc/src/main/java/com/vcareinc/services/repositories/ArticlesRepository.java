package com.vcareinc.services.repositories;

import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vcareinc.vo.Articles;

@Named
public interface ArticlesRepository extends JpaRepository<Articles, Long> {

	@Query(value="SELECT ar FROM Articles ar "
			+ "JOIN FETCH ar.category c "
			+ "JOIN ar.price p "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY p.priceType",
		countQuery="SELECT count(ar) FROM Articles ar "
			+ "JOIN ar.category c "
			+ "WHERE c.id = :categoryId")
	Page<Articles> findArticlesByCategoryOrderByPriceType(@Param("categoryId") Long categoryId, Pageable pageable);

	@Query(value="SELECT ar FROM Articles ar "
			+ "JOIN FETCH ar.category c "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY ar.title",
		countQuery="SELECT count(ar) FROM Articles ar "
			+ "JOIN ar.category c "
			+ "WHERE c.id = :categoryId")
	Page<Articles> findArticlesByCategoryOrderByTitle(@Param("categoryId") Long categoryId, Pageable pageable);
}
