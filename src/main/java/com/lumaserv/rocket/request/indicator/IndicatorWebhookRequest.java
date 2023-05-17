package com.lumaserv.rocket.request.indicator;

import lombok.Getter;
import org.javawebstack.validator.rule.RequiredRule;
@Getter
public class IndicatorWebhookRequest {

    @RequiredRule
    Double value;

}
