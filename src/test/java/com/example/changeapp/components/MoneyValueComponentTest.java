package com.example.changeapp.components;

import com.example.changeapp.component.MoneyValueComponent;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MoneyValueComponentTest {

    @InjectMocks
    private MoneyValueComponent moneyValueComponent;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Map<String, Double> mMap = new LinkedHashMap<>();
        mMap.put("pennies", 0.01);
        mMap.put("nickels", 0.05);
        mMap.put("dimes", 0.10);
        mMap.put("quarters", 0.25);
        ReflectionTestUtils.setField(moneyValueComponent, "moneyMap", mMap);
    }

    @Test()
    public void testGetValueForDenomination() {
        Double value = moneyValueComponent.getValueForDenomination("quarters");
        assertEquals(value.toString(), new Double(0.25).toString());
    }

    @Test()
    public void testGetCoinTypes() {
        Set<String> cointTypes = moneyValueComponent.getCoinTypes();
        assertEquals(cointTypes.size(), 4);
        Object[] coinTypeArr = cointTypes.toArray();
        assertEquals(coinTypeArr[0], "pennies");
        assertEquals(coinTypeArr[1], "nickels");
        assertEquals(coinTypeArr[2], "dimes");
        assertEquals(coinTypeArr[3], "quarters");
    }
}
