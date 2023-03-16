package com.technical.prices.application;

import com.technical.prices.domain.Price;
import com.technical.prices.domain.PriceRepository;
import com.technical.prices.domain.exception.InvalidDateException;
import com.technical.prices.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchPriceByBrandAndProductAtDateTest {
    private static final int BRAND_ID = 1;
    private static final int PRODUCT_ID = 35455;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
    private static final String START_DATE = "2020-06-15-00.00.00";
    private static final Date DATE = toDate(START_DATE);
    private static final String DATE_AS_STRING = START_DATE;
    private static final String BAD_DATE = "INVALID_DATE_VALUE";

    private static final int PRICE_ID = 3;
    private static final String END_DATE = "2020-06-15-11.00.00";
    private static final double PRICE = 30.50;
    private static final int PRIORITY = 1;
    private static final Currency CURRENCY = Currency.getInstance("EUR");

    @Mock
    private PriceRepository repository;

    private SearchPriceByBrandAndProductAtDate sut;

    @BeforeEach
    void setUp() {
        sut = new SearchPriceByBrandAndProductAtDate(repository, SIMPLE_DATE_FORMAT);
    }

    @Test
    public void should_return_valid_price() {
        SearchPriceByBrandAndProductAtDateRequest request = new SearchPriceByBrandAndProductAtDateRequest(BRAND_ID, PRODUCT_ID, DATE_AS_STRING);
        SearchPriceByBrandAndProductAtDateResponse expected = new SearchPriceByBrandAndProductAtDateResponse(
                BRAND_ID, PRODUCT_ID, PRICE_ID, START_DATE, END_DATE, PRICE
        );
        given(repository.searchByBrandProductAtDate(BRAND_ID, PRODUCT_ID, DATE)).willReturn(Optional.of(new Price(
                PRICE_ID, BRAND_ID, toDate(START_DATE), toDate(END_DATE), PRODUCT_ID, PRIORITY, PRICE, CURRENCY
        )));

        SearchPriceByBrandAndProductAtDateResponse result = sut.execute(request);

        assertEquals(expected, result);
    }

    @Test
    public void should_return_exception_when_price_not_found() {
        SearchPriceByBrandAndProductAtDateRequest request = new SearchPriceByBrandAndProductAtDateRequest(BRAND_ID, PRODUCT_ID, DATE_AS_STRING);
        given(repository.searchByBrandProductAtDate(BRAND_ID, PRODUCT_ID, DATE)).willReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> sut.execute(request));
    }

    @Test
    public void should_return_exception_when_date_is_not_parseable() {
        SearchPriceByBrandAndProductAtDateRequest request = new SearchPriceByBrandAndProductAtDateRequest(BRAND_ID, PRODUCT_ID, BAD_DATE);

        assertThrows(InvalidDateException.class, () -> sut.execute(request));
    }

    private static Date toDate(String date) {
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}