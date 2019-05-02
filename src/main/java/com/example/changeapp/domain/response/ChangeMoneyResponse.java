package com.example.changeapp.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeMoneyResponse {

    public Integer pennies;
    public Integer nickels;
    public Integer dimes;
    public Integer quarters;
}
