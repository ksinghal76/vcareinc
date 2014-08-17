package com.vcareinc.services.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vcareinc.vo.Listings;

public interface ListingRepository extends JpaRepository<Listings, Long> {

	@Query(value="SELECT l FROM Listings l "
			+ "JOIN l.category c "
			+ "JOIN l.price p"
			+ "JOIN FETCH l.address a "
			+ "JOIN FETCH a.country co "
			+ "JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY p.priceType",
		countQuery="SELECT l FROM Listings l "
			+ "JOIN l.category c "
			+ "WHERE c.id = :categoryId")
	Page<Listings> findListingsByCategoryOrderByPriceType(PageRequest request, @Param("categoryId") Long categoryId);
	
	@Query(value="SELECT l FROM Listings l "
			+ "JOIN l.category c "
			+ "JOIN FETCH l.address a "
			+ "JOIN FETCH a.country co "
			+ "JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY l.title")
	Page<Listings> findListingsByCategoryOrderByTitle(PageRequest request, @Param("categoryId") Long categoryId);
}
