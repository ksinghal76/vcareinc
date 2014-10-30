package com.vcareinc.services.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vcareinc.vo.Classified;

public interface ClassifiedRepository extends JpaRepository<Classified, Long> {

	@Query(value="SELECT cl FROM Classified cl "
			+ "JOIN FETCH cl.category c "
			+ "JOIN cl.price p "
			+ "LEFT OUTER JOIN FETCH cl.address a "
			+ "LEFT OUTER JOIN FETCH a.country co "
			+ "LEFT OUTER JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY p.priceType",
		countQuery="SELECT count(cl) FROM Classified cl "
			+ "JOIN cl.category c "
			+ "WHERE c.id = :categoryId")
	Page<Classified> findClassifiedByCategoryOrderByPriceType(@Param("categoryId") Long categoryId, Pageable pageable);

	@Query(value="SELECT cl FROM Classified cl "
			+ "JOIN FETCH cl.category c "
			+ "LEFT OUTER JOIN FETCH cl.address a "
			+ "LEFT OUTER JOIN FETCH a.country co "
			+ "LEFT OUTER JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY cl.title",
		countQuery="SELECT count(e) FROM Classified cl "
			+ "JOIN cl.category c "
			+ "WHERE c.id = :categoryId")
	Page<Classified> findClassifiedByCategoryOrderByTitle(@Param("categoryId") Long categoryId, Pageable pageable);
}
