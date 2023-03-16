package com.inditext.prices.infra.api;

import com.inditext.prices.PricesApplication;
import com.inditext.prices.application.SearchPriceByBrandAndProductAtDateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

@SpringBootTest(
        classes = PricesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
public class PriceControllerAcceptanceTest {
    @Autowired
    private WebTestClient webTestClient;


    @ParameterizedTest
    @MethodSource("testQueries")
    public void should_return_the_right_response(
            long brandId,
            long productId,
            String date,
            SearchPriceByBrandAndProductAtDateResponse expected
    ) {
        EntityExchangeResult<SearchPriceByBrandAndProductAtDateResponse> response = webTestClient.get()
                .uri(
                        String.format("/price_at_date?brand_id=%s&product_id=%s&date=%s",
                                brandId,
                                productId,
                                date
                        )
                )
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(SearchPriceByBrandAndProductAtDateResponse.class)
                .returnResult();

        Assertions.assertEquals(expected, response.getResponseBody());
    }


    private static Stream<Arguments> testQueries() {
        return Stream.of(
                Arguments.of(1, 35455, "2020-06-14-10.00.00", new SearchPriceByBrandAndProductAtDateResponse(
                        1, 35455, 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", 35.5
                )),
                Arguments.of(1, 35455, "2020-06-14-16.00.00", new SearchPriceByBrandAndProductAtDateResponse(
                        1, 35455, 2, "2020-06-14-15.00.00", "2020-06-14-18.30.00", 25.45
                )),
                Arguments.of(1, 35455, "2020-06-14-21.00.00", new SearchPriceByBrandAndProductAtDateResponse(
                        1, 35455, 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", 35.5
                )),
                Arguments.of(1, 35455, "2020-06-15-10.00.00", new SearchPriceByBrandAndProductAtDateResponse(
                        1, 35455, 3, "2020-06-15-00.00.00", "2020-06-15-11.00.00", 30.5
                )),
                Arguments.of(1, 35455, "2020-06-16-21.00.00", new SearchPriceByBrandAndProductAtDateResponse(
                        1, 35455, 4, "2020-06-15-16.00.00", "2020-12-31-23.59.59", 38.95
                ))
        );
    }

}
