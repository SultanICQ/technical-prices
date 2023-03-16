package com.inditext.prices.infra.config;

import com.inditext.prices.application.SearchPriceByBrandAndProductAtDate;
import com.inditext.prices.domain.PriceRepository;
import com.inditext.prices.infra.repository.H2CrudPriceRepository;
import com.inditext.prices.infra.repository.H2PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class Service {

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
    }

    @Bean
    public PriceRepository priceRepository(H2CrudPriceRepository repository, SimpleDateFormat dateFormat) {
        return new H2PriceRepository(repository, dateFormat);
    }

    @Bean
    public SearchPriceByBrandAndProductAtDate searchPriceByBrandAndProductAtDate(PriceRepository repository, SimpleDateFormat dateFormat) {
        return new SearchPriceByBrandAndProductAtDate(repository, dateFormat);
    }
}
