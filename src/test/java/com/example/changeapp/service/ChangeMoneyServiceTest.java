package com.example.changeapp.service;

import com.example.changeapp.component.MoneyValueComponent;
import com.example.changeapp.dao.MachineDAO;
import com.example.changeapp.domain.request.ChangeMoneyRequest;
import com.example.changeapp.domain.response.ChangeMoneyResponse;
import com.example.changeapp.entity.Machine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChangeMoneyServiceTest {

    @Mock
    private MachineDAO machineDAO;

    @Mock
    private MoneyValueComponent moneyValueComponent;

    @InjectMocks
    private ChangeMoneyService changeMoneyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void changeMoney_success() {
        ChangeMoneyRequest changeMoneyRequest = ChangeMoneyRequest.builder()
                .one(4)
                .two(0)
                .five(0)
                .ten(0)
                .twenty(0)
                .fifty(0)
                .hundred(0)
                .build();

        Machine machine = Machine.builder()
                .id(new Integer(1).longValue())
                .quarters(4)
                .dimes(10)
                .nickels(20)
                .pennies(100)
                .build();

        when(machineDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(machine));

        Set<String> coinTypes = new LinkedHashSet<>();
        coinTypes.add("quarters");
        coinTypes.add("dimes");
        coinTypes.add("nickels");
        coinTypes.add("pennies");
        when(moneyValueComponent.getCoinTypes()).thenReturn(coinTypes);

        when(moneyValueComponent.getValueForDenomination("quarters")).thenReturn(0.25);
        when(moneyValueComponent.getValueForDenomination("dimes")).thenReturn(0.10);
        when(moneyValueComponent.getValueForDenomination("nickels")).thenReturn(0.05);
        when(moneyValueComponent.getValueForDenomination("pennies")).thenReturn(0.01);

        ChangeMoneyResponse changeMoneyResponse = changeMoneyService.changeMoney(changeMoneyRequest);

        assertEquals(changeMoneyResponse.getQuarters().intValue(), 4);
        assertEquals(changeMoneyResponse.getDimes().intValue(), 10);
        assertEquals(changeMoneyResponse.getNickels().intValue(), 20);
        assertEquals(changeMoneyResponse.getPennies().intValue(), 100);
    }
}
