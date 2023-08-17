package com.example.catalogOfCars.utils;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class QueryFilterUtil {

    private static String queryStart = "select c from Car c where ";


    public static String getQuery(Map<String, ArrayList<String>> filter) {
        int i = 0;
        for (String val : filter.keySet()) {
            i++;
            List<String> par = filter.get(val);
            String partQuery = val;
            if (par.size() > 1) {
                partQuery = partQuery + " IN (";
                String parF = par.stream().map(item -> (" '" + item + "', ")).collect(Collectors.joining(" "));

                partQuery = partQuery + parF.substring(0, parF.length() - 2) + ") ";
            } else if (par.size() == 1) {
                String parF = par.stream().map(item -> (" = '" + item + "' ")).collect(Collectors.joining(" "));
                partQuery = partQuery + parF;

            }
            queryStart = queryStart + partQuery;
            if (filter.keySet().size() > i) {
                queryStart = queryStart + " and ";
            }
        }
        return queryStart;
    }
}
