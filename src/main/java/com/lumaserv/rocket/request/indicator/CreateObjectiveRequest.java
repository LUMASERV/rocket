package com.lumaserv.rocket.request.indicator;

import lombok.Getter;
import org.javawebstack.validator.Rule;
import org.javawebstack.validator.rule.RequiredRule;
import org.javawebstack.validator.rule.StringRule;
import org.javawebstack.validator.rule.UUIDRule;

import java.util.UUID;
@Getter
public class CreateObjectiveRequest {
        @RequiredRule
        UUID projectId;
        @RequiredRule
        @StringRule(min = 2, max = 50)
        String name;
        @Rule({})
        Double value;
        @UUIDRule()
        UUID indicatorID;
}
