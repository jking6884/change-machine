package com.example.changeapp.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMoneyRequest {

    public Integer id;
    public Integer pennies;
    public Integer nickels;
    public Integer dimes;
    public Integer quarters;
}
