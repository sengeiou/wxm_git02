package com.splan.bplan.result;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WalletResult implements Serializable {

    private String extraId;

    private String clientId;

    private BigDecimal coin;

}
