package ual.tfg.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PurchaseUnit {

    @JsonProperty("reference_id")
    String referenceId;
    @JsonProperty("amount")
    Unit unit;

}

@Data
class Unit {
    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("value")
    private String value;
}