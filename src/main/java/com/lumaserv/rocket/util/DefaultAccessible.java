package com.lumaserv.rocket.util;

import org.javawebstack.orm.Accessible;
import org.javawebstack.orm.Model;
import org.javawebstack.orm.query.Query;
import org.javawebstack.orm.query.QueryGroup;

public class DefaultAccessible implements Accessible {
    public <T extends Model> QueryGroup<T> access(Query<T> query, QueryGroup<T> queryGroup, Object accessor) {
        return queryGroup;
    }
}
