package com.example.changeapp.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeMoneyRequest {

    public Integer one = 0;
    public Integer two = 0;
    public Integer five = 0;
    public Integer ten = 0;
    public Integer twenty = 0;
    public Integer fifty = 0;
    public Integer hundred = 0;

    public Double getSumTotal() {
        Integer sum = (1 * one) +
                (2 * two) +
                (5 * five) +
                (10 * ten) +
                (20 * twenty) +
                (50 * fifty) +
                (100 * hundred);
        return sum.doubleValue();
    }
}
