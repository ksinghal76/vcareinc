package com.vcareinc.services.repositories;

import java.sql.Timestamp;

import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vcareinc.vo.Events;

@Named
public interface EventRepository extends JpaRepository<Events, Long> {

	@Query(value="SELECT e FROM Events e "
			+ "JOIN FETCH e.category c "
			+ "JOIN e.price p "
			+ "JOIN FETCH e.address a "
			+ "JOIN FETCH a.country co "
			+ "JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY p.priceType",
		countQuery="SELECT count(e) FROM Events e "
			+ "JOIN e.category c "
			+ "WHERE c.id = :categoryId")
	Page<Events> findEventsByCategoryOrderByPriceType(@Param("categoryId") Long categoryId, Pageable pageable);

	@Query(value="SELECT e FROM Events e "
			+ "JOIN FETCH e.category c "
			+ "JOIN FETCH e.address a "
			+ "JOIN FETCH a.country co "
			+ "JOIN FETCH a.state s "
			+ "WHERE c.id = :categoryId "
			+ "ORDER BY e.title",
		countQuery="SELECT count(e) FROM Events e "
			+ "JOIN e.category c "
			+ "WHERE c.id = :categoryId")
	Page<Events> findEventsByCategoryOrderByTitle(@Param("categoryId") Long categoryId, Pageable pageable);

	@Query(value="SELECT e FROM Events e "
			+ "JOIN FETCH e.category c "
			+ "LEFT OUTER JOIN FETCH e.address a "
			+ "LEFT OUTER JOIN FETCH a.country co "
			+ "LEFT OUTER JOIN FETCH a.state s "
			+ "WHERE e.startDate <= :date and e.endDate >= :date"
			+ " ORDER BY e.title",
			countQuery="SELECT count(e) FROM Events e "
					+ "JOIN e.category c "
					+ "WHERE e.startDate <= :date and e.endDate >= :date")
	Page<Events> findEventsByDateOrderByPriceType(@Param("date") Timestamp date, Pageable pageable);
}
