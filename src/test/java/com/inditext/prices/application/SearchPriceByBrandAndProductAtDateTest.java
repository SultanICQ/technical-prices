package com.inditext.prices.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchPriceByBrandAndProductAtDateTest {

    SearchPriceByBrandAndProductAtDate sut;

    @BeforeEach
    void setUp() {
        sut = new SearchPriceByBrandAndProductAtDate();
    }

    @Test
    public void should_return_valid_price() {
        SearchPriceByBrandAndProductAtDateRequest request = new SearchPriceByBrandAndProductAtDateRequest();
        SearchPriceByBrandAndProductAtDateResponse expected = new SearchPriceByBrandAndProductAtDateResponse();

        SearchPriceByBrandAndProductAtDateResponse result = sut.execute(request);

        assertEquals( expected, result );
    }

}