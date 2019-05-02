package com.example.changeapp.service;

import com.example.changeapp.dao.MachineDAO;
import com.example.changeapp.domain.request.AddMoneyRequest;
import com.example.changeapp.domain.response.ManageMoneyResponse;
import com.example.changeapp.entity.Machine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ManageMoneyServiceTest {

    @Mock
    MachineDAO machineDAO;

    @InjectMocks
    ManageMoneyService manageMoneyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddMoney_success() {
        AddMoneyRequest addMoneyRequest = AddMoneyRequest.builder()
                .id(1)
                .pennies(2)
                .nickels(2)
                .dimes(2)
                .quarters(2)
                .build();

        Machine machine = Machine.builder()
                .id(new Integer(1).longValue())
                .quarters(2)
                .dimes(2)
                .nickels(2)
                .pennies(2)
                .build();

        when(machineDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(machine));

        ManageMoneyResponse manageMoneyResponse = manageMoneyService.addMoney(addMoneyRequest);

        assertEquals(manageMoneyResponse.getQuarters().intValue(), 4);
        assertEquals(manageMoneyResponse.getDimes().intValue(), 4);
        assertEquals(manageMoneyResponse.getNickels().intValue(), 4);
        assertEquals(manageMoneyResponse.getPennies().intValue(), 4);
    }
}
