package com.example.changeapp.controller;

import com.example.changeapp.domain.request.AddMoneyRequest;
import com.example.changeapp.service.ManageMoneyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class ManageMachineController {

    @Mock
    private ManageMoneyService manageMoneyService;

    @InjectMocks
    private ManageMachineController manageMachineController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddMoneyToMachine() {
        AddMoneyRequest addMoneyRequest = AddMoneyRequest.builder()
                .quarters(100)
                .dimes(100)
                .nickels(100)
                .pennies(100)
                .build();

        manageMoneyService.addMoney(addMoneyRequest);
        verify(manageMoneyService, times(1)).addMoney(any(AddMoneyRequest.class));
    }
}
