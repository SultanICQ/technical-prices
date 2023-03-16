package com.inditext.prices.application;

import com.inditext.prices.domain.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchPriceByBrandAndProductAtDateTest {
    private static final int BRAND_ID = 1;
    private static final int PRODUCT_ID = 35455;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Date DATE = toDate("2020-06-15 00:00:00");

    @Mock
    private PriceRepository repository;

    private SearchPriceByBrandAndProductAtDate sut;

    @BeforeEach
    void setUp() {
        sut = new SearchPriceByBrandAndProductAtDate(repository);
    }

    @Test
    public void should_return_valid_price() {
        SearchPriceByBrandAndProductAtDateRequest request = new SearchPriceByBrandAndProductAtDateRequest();
        SearchPriceByBrandAndProductAtDateResponse expected = new SearchPriceByBrandAndProductAtDateResponse();
        given(repository.searchByBrandProductAtDate(BRAND_ID, PRODUCT_ID, DATE));

        SearchPriceByBrandAndProductAtDateResponse result = sut.execute(request);

        assertEquals(expected, result);
    }

    private static Date toDate(String date) {
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}