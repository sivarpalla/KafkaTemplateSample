package com.americanfirstfinance.kafkatest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealerMasterResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private long dealerNumber;
    private String dealerStatus;
    private String dealerName;
    private String dealerSSN;
    private String dealerProduct;
    private long dealerLocationId;
    private long dealerEftABA;
    private String dealerEftAccount;
    private String dealerEftType;
    private String dealerDecisionModel;
    private String dealerGroup;

}
