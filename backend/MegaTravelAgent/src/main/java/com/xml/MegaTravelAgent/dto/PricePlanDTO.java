package com.xml.MegaTravelAgent.dto;

import java.util.ArrayList;
import java.util.Collection;

public class PricePlanDTO {

    public Collection<SpecificPriceDTO> specificPrices = new ArrayList<>();
    public double defaultPrice;

    public PricePlanDTO() {
    }

    public PricePlanDTO(Collection<SpecificPriceDTO> specificPrices, double defaultPrice) {
        this.specificPrices = specificPrices;
        this.defaultPrice = defaultPrice;
    }

    public Collection<SpecificPriceDTO> getSpecificPrices() {
        return specificPrices;
    }

    public void setSpecificPrices(Collection<SpecificPriceDTO> specificPrices) {
        this.specificPrices = specificPrices;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
