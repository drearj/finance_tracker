package dev.andrearaujo.dto;

import java.util.List;

public class ExchangeRateResponseDTO {
    private List<ExchangeRateDataDTO> data;
    private ExchangeRateMetaDTO meta;
    private ExchangeRateLinksDTO links;

    public List<ExchangeRateDataDTO> getData() {
        return data;
    }
    public void setData(List<ExchangeRateDataDTO> data) {
        this.data = data;
    }

    public ExchangeRateMetaDTO getMeta() {
        return meta;
    }
    public void setMeta(ExchangeRateMetaDTO meta) {
        this.meta = meta;
    }

    public ExchangeRateLinksDTO getLinks() {
        return links;
    }
    public void setLinks(ExchangeRateLinksDTO links) {
        this.links = links;
    }
}

class ExchangeRateMetaDTO {
    private int count;
    private ExchangeRateLabelsDTO labels;
    private ExchangeRateDataTypesDTO dataTypes;
    private ExchangeRateDataFormatsDTO dataFormats;
    private int total_count;
    private int total_pages;

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public ExchangeRateLabelsDTO getLabels() {
        return labels;
    }
    public void setLabels(ExchangeRateLabelsDTO labels) {
        this.labels = labels;
    }

    public ExchangeRateDataTypesDTO getDataTypes() {
        return dataTypes;
    }
    public void setDataTypes(ExchangeRateDataTypesDTO dataTypes) {
        this.dataTypes = dataTypes;
    }

    public ExchangeRateDataFormatsDTO getDataFormats() {
        return dataFormats;
    }
    public void setDataFormats(ExchangeRateDataFormatsDTO dataFormats) {
        this.dataFormats = dataFormats;
    }

    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_pages() {
        return total_pages;
    }
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}

class ExchangeRateLinksDTO {
    private String self;
    private String first;
    private String prev;
    private String next;
    private String last;

    public String getSelf() {
        return self;
    }
    public void setSelf(String self) {
        this.self = self;
    }

    public String getFirst() {
        return first;
    }
    public void setFirst(String first) {
        this.first = first;
    }

    public String getPrev() {
        return prev;
    }
    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }
    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }
}

class ExchangeRateLabelsDTO {
    private String country;
    private String currency;
    private String exchange_rate;
    private String effective_date;

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }
    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }
}

class ExchangeRateDataTypesDTO {
    private String country;
    private String currency;
    private String exchange_rate;
    private String effective_date;

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }
    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }
}

class ExchangeRateDataFormatsDTO {
    private String country;
    private String currency;
    private String exchange_rate;
    private String effective_date;

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }
    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }
}