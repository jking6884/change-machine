package com.example.changeapp.controller;

import com.example.changeapp.domain.request.ChangeMoneyRequest;
import com.example.changeapp.domain.response.ChangeMoneyResponse;
import com.example.changeapp.service.ChangeMoneyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

public class ChangeControllerTest {

    @Mock
    private ChangeMoneyService changeMoneyService;

    @InjectMocks
    private ChangeController changeController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testChangeMoney() {
        ChangeMoneyResponse changeMoneyResponse = ChangeMoneyResponse.builder()
                .quarters(4)
                .dimes(0)
                .nickels(0)
                .pennies(0)
                .build();

        ChangeMoneyRequest changeMoneyRequest = ChangeMoneyRequest.builder()
                .one(1)
                .two(0)
                .five(0)
                .ten(0)
                .twenty(0)
                .fifty(0)
                .hundred(0)
                .build();

        when(changeMoneyService.changeMoney(any(ChangeMoneyRequest.class))).thenReturn(changeMoneyResponse);
        changeController.changeMoney(changeMoneyRequest);
        verify(changeMoneyService, times(1)).changeMoney(any(ChangeMoneyRequest.class));
    }
}
