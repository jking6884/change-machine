package com.example.changeapp.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class MoneyValueComponent {

    @Value("${coins.preference}")
    private String coinPreference;

    private Map<String, Double> moneyMap;

    @PostConstruct
    public void init() {
        Map<String, Double> mMap = new LinkedHashMap<>();

        if (!coinPreference.isEmpty() && coinPreference.equals("MOST_COINS")) {
            mMap.put("pennies", 0.01);
            mMap.put("nickels", 0.05);
            mMap.put("dimes", 0.10);
            mMap.put("quarters", 0.25);
        } else {
            mMap.put("quarters", 0.25);
            mMap.put("dimes", 0.10);
            mMap.put("nickels", 0.05);
            mMap.put("pennies", 0.01);
        }

        moneyMap = Collections.unmodifiableMap(mMap);
    }

    public Double getValueForDenomination(String denomination) {
        if (!moneyMap.containsKey(denomination)) {
            throw new RuntimeException("Invalid denomination requested: (" + denomination + ")");
        }

        return moneyMap.get(denomination);
    }

    public Set<String> getCoinTypes() {
        return moneyMap.keySet();
    }
}
