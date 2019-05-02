package com.example.changeapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinCalcResult {

    public Double denomination;
    public Integer coinsUsed;
    public String coinType;
}
