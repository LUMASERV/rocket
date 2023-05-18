package com.lumaserv.rocket.request.project;

import lombok.Getter;
import org.javawebstack.validator.rule.RequiredRule;
import org.javawebstack.validator.rule.StringRule;

@Getter
public class CreateProjectRequest {

    @RequiredRule
    @StringRule(min = 2, max = 50)
    String name;

}
