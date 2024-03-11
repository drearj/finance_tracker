package dev.andrearaujo.dto;

public class ExchangeRateMetaDTO {
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
