package com.vcareinc.services.repositories;

import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vcareinc.vo.Listings;

@Named
public interface ListingRepository extends JpaRepository<Listings, Long> {

	@Query(value="SELECT l FROM Listings l "
			+ "JOIN FETCH l.category c "
			+ "JOIN l.price p "
			+ "LEFT OUTER JOIN FETCH l.address a "
			+ "LEFT OUTER JOIN FETCH a.country co "
			+ "LEFT OUTER JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY p.priceType",
		countQuery="SELECT count(l) FROM Listings l "
			+ "JOIN l.category c "
			+ "WHERE c.id = :categoryId")
	Page<Listings> findListingsByCategoryOrderByPriceType(@Param("categoryId") Long categoryId, Pageable pageable);

	@Query(value="SELECT l FROM Listings l "
			+ "JOIN FETCH l.category c "
			+ "LEFT OUTER JOIN FETCH l.address a "
			+ "LEFT OUTER JOIN FETCH a.country co "
			+ "LEFT OUTER JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY l.title",
		countQuery="SELECT count(l) FROM Listings l "
			+ "JOIN l.category c "
			+ "WHERE c.id = :categoryId")
	Page<Listings> findListingsByCategoryOrderByTitle(@Param("categoryId") Long categoryId, Pageable pageable);
}
