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

