package com.example.changeapp.entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

public class MachineTest {

    @InjectMocks
    private Machine machine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(machine, "pennies", 100);
        ReflectionTestUtils.setField(machine, "nickels", 100);
        ReflectionTestUtils.setField(machine, "dimes", 100);
        ReflectionTestUtils.setField(machine, "quarters", 100);
    }

    @Test
    public void testGetCoinCountByString() {
        Integer qCount = machine.getCoinCountByString("quarters");
        assertEquals(qCount.intValue(), 100);

        Integer nCount = machine.getCoinCountByString("quarters");
        assertEquals(nCount.intValue(), 100);

        Integer dCount = machine.getCoinCountByString("quarters");
        assertEquals(dCount.intValue(), 100);

        Integer pCount = machine.getCoinCountByString("quarters");
        assertEquals(pCount.intValue(), 100);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCoinCountByStringWithInvalidName() {
        Integer qCount = machine.getCoinCountByString("sgjdslk");
    }
}
