package com.inditext.prices.infra.api;

import com.inditext.prices.application.SearchPriceByBrandAndProductAtDate;
import com.inditext.prices.application.SearchPriceByBrandAndProductAtDateRequest;
import com.inditext.prices.application.SearchPriceByBrandAndProductAtDateResponse;
import com.inditext.prices.domain.exception.InvalidDateException;
import com.inditext.prices.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {
    private static final int BRAND_ID = 1;
    private static final int PRODUCT_ID = 35455;
    private static final String START_DATE = "2020-06-15-00.00.00";
    private static final String DATE_AS_STRING = START_DATE;
    private static final String BAD_DATE = "INVALID_DATE_VALUE";
    private static final int PRICE_ID = 3;
    private static final String END_DATE = "2020-06-15-11.00.00";
    private static final double PRICE = 30.50;
    private static final SearchPriceByBrandAndProductAtDateRequest REQUEST = new SearchPriceByBrandAndProductAtDateRequest(BRAND_ID, PRODUCT_ID, DATE_AS_STRING);
    private static final SearchPriceByBrandAndProductAtDateRequest INVALID_REQUEST = new SearchPriceByBrandAndProductAtDateRequest(BRAND_ID, PRODUCT_ID, BAD_DATE);
    private static final SearchPriceByBrandAndProductAtDateResponse RESPONSE = new SearchPriceByBrandAndProductAtDateResponse(
            BRAND_ID, PRODUCT_ID, PRICE_ID, START_DATE, END_DATE, PRICE
    );
    @Mock
    private SearchPriceByBrandAndProductAtDate searchPriceByBrandAndProductAtDate;

    private PriceController sut;

    @BeforeEach
    void setUp() {
        sut = new PriceController(searchPriceByBrandAndProductAtDate);
    }

    @Test
    public void should_return_valid_price_when_valid_request() {
        given(searchPriceByBrandAndProductAtDate.execute(REQUEST)).willReturn(RESPONSE);
        ResponseEntity<SearchPriceByBrandAndProductAtDateResponse> expected = ResponseEntity.ok().body(RESPONSE);

        ResponseEntity<SearchPriceByBrandAndProductAtDateResponse> response = sut.searchPriceByBrandAndProductAtDate(BRAND_ID, PRODUCT_ID, DATE_AS_STRING);

        assertEquals(expected, response);
    }

    @Test
    public void should_return_exception_if_price_not_exist() {
        given(searchPriceByBrandAndProductAtDate.execute(REQUEST)).willThrow(PriceNotFoundException.class);

        assertThrows(PriceNotFoundException.class, () -> sut.searchPriceByBrandAndProductAtDate(BRAND_ID, PRODUCT_ID, DATE_AS_STRING) );
    }

    @Test
    public void should_return_exception_if_dates_are_not_parseable() {
        given(searchPriceByBrandAndProductAtDate.execute(INVALID_REQUEST)).willThrow(InvalidDateException.class);

        assertThrows(InvalidDateException.class, () -> sut.searchPriceByBrandAndProductAtDate(BRAND_ID, PRODUCT_ID, BAD_DATE) );
    }

}