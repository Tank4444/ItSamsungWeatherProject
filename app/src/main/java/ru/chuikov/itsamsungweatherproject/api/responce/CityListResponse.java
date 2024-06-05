package ru.chuikov.itsamsungweatherproject.api.responce;

import com.google.gson.annotations.JsonAdapter;
import com.squareup.moshi.JsonClass;

import java.util.List;


public class CityListResponse {

    private List<Result> results;
    private Double generationtimeMs;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Double getGenerationtimeMs() {
        return generationtimeMs;
    }

    public void setGenerationtimeMs(Double generationtimeMs) {
        this.generationtimeMs = generationtimeMs;
    }

    public static class Result{
        private Integer id;
        private String name;
        private Double latitude;
        private Double longitude;
        private Double elevation;
        private String featureCode;
        private String countryCode;
        private Integer admin1Id;
        private Integer admin3Id;
        private Integer admin4Id;
        private String timezone;
        private Integer population;
        private List<String> postcodes;
        private Integer countryId;
        private String country;
        private String admin1;
        private String admin3;
        private String admin4;
        private Integer admin2Id;
        private String admin2;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getElevation() {
            return elevation;
        }

        public void setElevation(Double elevation) {
            this.elevation = elevation;
        }

        public String getFeatureCode() {
            return featureCode;
        }

        public void setFeatureCode(String featureCode) {
            this.featureCode = featureCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public Integer getAdmin1Id() {
            return admin1Id;
        }

        public void setAdmin1Id(Integer admin1Id) {
            this.admin1Id = admin1Id;
        }

        public Integer getAdmin3Id() {
            return admin3Id;
        }

        public void setAdmin3Id(Integer admin3Id) {
            this.admin3Id = admin3Id;
        }

        public Integer getAdmin4Id() {
            return admin4Id;
        }

        public void setAdmin4Id(Integer admin4Id) {
            this.admin4Id = admin4Id;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public Integer getPopulation() {
            return population;
        }

        public void setPopulation(Integer population) {
            this.population = population;
        }

        public List<String> getPostcodes() {
            return postcodes;
        }

        public void setPostcodes(List<String> postcodes) {
            this.postcodes = postcodes;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAdmin1() {
            return admin1;
        }

        public void setAdmin1(String admin1) {
            this.admin1 = admin1;
        }

        public String getAdmin3() {
            return admin3;
        }

        public void setAdmin3(String admin3) {
            this.admin3 = admin3;
        }

        public String getAdmin4() {
            return admin4;
        }

        public void setAdmin4(String admin4) {
            this.admin4 = admin4;
        }

        public Integer getAdmin2Id() {
            return admin2Id;
        }

        public void setAdmin2Id(Integer admin2Id) {
            this.admin2Id = admin2Id;
        }

        public String getAdmin2() {
            return admin2;
        }

        public void setAdmin2(String admin2) {
            this.admin2 = admin2;
        }
    }

}
