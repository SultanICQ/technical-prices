package com.inditext.prices.infra.api;

import com.inditext.prices.application.SearchPriceByBrandAndProductAtDate;
import com.inditext.prices.application.SearchPriceByBrandAndProductAtDateRequest;
import com.inditext.prices.application.SearchPriceByBrandAndProductAtDateResponse;
import com.inditext.prices.domain.exception.InvalidDateException;
import com.inditext.prices.domain.exception.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class PriceController {
    private final SearchPriceByBrandAndProductAtDate searchPriceByBrandAndProductAtDate;

    public PriceController(@Autowired SearchPriceByBrandAndProductAtDate searchPriceByBrandAndProductAtDate) {
        this.searchPriceByBrandAndProductAtDate = searchPriceByBrandAndProductAtDate;
    }

    @GetMapping("price_at_date")
    @ResponseBody
    public ResponseEntity<SearchPriceByBrandAndProductAtDateResponse> searchPriceByBrandAndProductAtDate(
            @RequestParam("brand_id") long brandId,
            @RequestParam("product_id") long productId,
            @RequestParam("date") String date
    ) {
        return ResponseEntity.ok().body(
                searchPriceByBrandAndProductAtDate.execute(new SearchPriceByBrandAndProductAtDateRequest(
                        brandId,
                        productId,
                        date
                ))
        );
    }
}
