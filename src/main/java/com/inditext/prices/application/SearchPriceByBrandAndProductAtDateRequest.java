package com.inditext.prices.application;

import java.util.Date;

public record SearchPriceByBrandAndProductAtDateRequest(
        long brandId,
        long productId,
        String date
) {}
