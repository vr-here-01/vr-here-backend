package com.vr.here.api.gateway.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private Name name;
    private List<String> tld;
    private String cca2;
    private String ccn3;
    private String cca3;
    private boolean independent;
    private String status;
    private boolean unMember;
    private Map<String, Currency> currencies;
    private Idd idd;
    private List<String> capital;
    private List<String> altSpellings;
    private String region;
    private Map<String, String> languages;
    private Map<String, Translation> translations;
    private double[] latlng;
    private boolean landlocked;
    private double area;
    private Demonyms demonyms;
    private String flag;
    private Maps maps;
    private int population;
    private Car car;
    private List<String> timezones;
    private List<String> continents;
    private Flag flags;
    private CoatOfArms coatOfArms;
    private String startOfWeek;
    private CapitalInfo capitalInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Name {
        private String common;
        private String official;
        private Map<String, NativeName> nativeName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NativeName {
        private String official;
        private String common;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Currency {
        private String name;
        private String symbol;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Idd {
        private String root;
        private List<String> suffixes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Demonyms {
        private Eng eng;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Eng {
            private String f;
            private String m;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Maps {
        private String googleMaps;
        private String openStreetMaps;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Car {
        private List<String> signs;
        private String side;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Flag {
        private String png;
        private String svg;
    }

    @Data
    @NoArgsConstructor
    public static class CoatOfArms {
        // Placeholder, as no data was given for the coat of arms in the JSON
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Translation {
        private String official;
        private String common;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CapitalInfo {
        private double[] latlng;
    }
}
