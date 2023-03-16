package com.inditext.prices.infra.repository;

import com.inditext.prices.domain.Price;
import com.inditext.prices.domain.PriceRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface H2CrudPriceRepository extends CrudRepository<H2Price, Long> {
    @Query(
            """
                SELECT p
                FROM H2Price p
                WHERE p.brandId = ?1
                    AND p.productId = ?2
                    AND ?3 BETWEEN p.start AND p.end
                ORDER BY p.priority DESC
                LIMIT 1
            """
    )
    Optional<H2Price> findPriceByBrandIdAndProductIdAndDate(long brandId, long productId, String date);
}
