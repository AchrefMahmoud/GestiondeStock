package com.tn.GestiondeStock.Interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
            if (sql.contains("where")) {
                sql = sql + " and identreprise = 1";
            } else {
                sql = sql + " where identreprise = 1";
            }
        }
        return super.onPrepareStatement(sql);
    }
}