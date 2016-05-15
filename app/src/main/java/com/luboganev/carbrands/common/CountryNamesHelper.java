package com.luboganev.carbrands.common;

import java.util.HashMap;
import java.util.Random;

/**
 * This class helps with mapping country code to a country name. It related only to the mock data
 * used in this demo and has nothing to do with the application architecture itself.
 *
 * Created by luboganev on 24/04/2015
 */
public class CountryNamesHelper {
    private static final HashMap<String, String> mappingCode2Name = new HashMap<>();
    private static final HashMap<String, String> mappingName2Code = new HashMap<>();

    private static final String[] countryCodesArray = {
            "FR", "DE", "IT", "JP", "SE", "GB", "US"
    };

    private static final String[] countryNamesArray = {
            "France", "Germany", "Italy", "Japan", "Sweden",
            "United Kingdom of Great Britain and Northern Ireland", "United States of America"
    };

    public static String getCountryCode(String countryName) {
        if (mappingName2Code.isEmpty()) {
            generateMapping();
        }
        return mappingName2Code.get(countryName);
    }

    public static String getCountryName(String countryCode) {
        if (mappingCode2Name.isEmpty()) {
            generateMapping();
        }
        return mappingCode2Name.get(countryCode);
    }

    private static void generateMapping() {
        for (int i = 0; i < Math.min(countryNamesArray.length , countryCodesArray.length); i++) {
            mappingName2Code.put(countryNamesArray[i], countryCodesArray[i]);
            mappingCode2Name.put(countryCodesArray[i], countryNamesArray[i]);
        }
    }

    private static Random rand = new Random(System.currentTimeMillis());

    public static String getRandomCountryCode() {
        return countryCodesArray[rand.nextInt(countryCodesArray.length - 1)];
    }
}
