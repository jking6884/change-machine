package com.example.changeapp.service;

import com.example.changeapp.component.MoneyValueComponent;
import com.example.changeapp.dao.MachineDAO;
import com.example.changeapp.domain.CoinCalcResult;
import com.example.changeapp.domain.request.ChangeMoneyRequest;
import com.example.changeapp.domain.response.ChangeMoneyResponse;
import com.example.changeapp.entity.Machine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ChangeMoneyService {

    private MachineDAO machineDAO;

    private MoneyValueComponent moneyValueComponent;

    @Autowired
    public ChangeMoneyService(MachineDAO machineDAO, MoneyValueComponent moneyValueComponent) {
        this.machineDAO = machineDAO;
        this.moneyValueComponent = moneyValueComponent;
    }

    public ChangeMoneyResponse changeMoney(ChangeMoneyRequest changeMoneyRequest) {
        Double sumTotalMoney = changeMoneyRequest.getSumTotal();
        Machine machine = machineDAO.findById(new Integer(1).longValue()).get();
        return calculateChange(sumTotalMoney, machine);
    }

    private ChangeMoneyResponse calculateChange(Double sumTotalMoney, Machine machine) {
        List<CoinCalcResult> coinCalcResults = new ArrayList<>();
        Double totalMoneyAccountedFor = 0.0;

        Set<String> coinTypes = moneyValueComponent.getCoinTypes();

        for (String coinType : coinTypes) {
            if ((sumTotalMoney - totalMoneyAccountedFor) > 0) {
                Integer numberOfCoinsOfType = machine.getCoinCountByString(coinType);
                if (numberOfCoinsOfType > 0) {
                    CoinCalcResult coinCalcResult = calculateNeededCoins(sumTotalMoney, coinType, numberOfCoinsOfType, totalMoneyAccountedFor);
                    totalMoneyAccountedFor += (coinCalcResult.getCoinsUsed() * coinCalcResult.getDenomination());
                    coinCalcResults.add(coinCalcResult);
                }
            }
        }

        if (totalMoneyAccountedFor < sumTotalMoney) {
            throw new RuntimeException("Insufficient funds in machine at this time");
        }

        saveNewValuesInMachine(machine, coinCalcResults);

        return buildChangeMoneyResponse(sumTotalMoney, totalMoneyAccountedFor, coinCalcResults);
    }

    private CoinCalcResult calculateNeededCoins(Double sumTotalMoney, String denomination, Integer coinsAvailable, Double totalMoneyAccountedFor) {
        Double denominationValue = moneyValueComponent.getValueForDenomination(denomination);
        Double totalRemainingMoney = sumTotalMoney - totalMoneyAccountedFor;
        CoinCalcResult coinCalcResult = CoinCalcResult.builder()
                .denomination(denominationValue)
                .coinType(denomination)
                .build();

        Integer coinsNeeded = (int)(totalRemainingMoney / denominationValue);
        if (coinsNeeded >= coinsAvailable) {
            coinCalcResult.setCoinsUsed(coinsAvailable);
        } else if (coinsNeeded < coinsAvailable) {
            coinCalcResult.setCoinsUsed(coinsNeeded);
        }

        return coinCalcResult;
    }

    private void saveNewValuesInMachine(Machine machine, List<CoinCalcResult> coinCalcResults) {
        for (CoinCalcResult coinCalcResult : coinCalcResults) {
            switch (coinCalcResult.getCoinType()) {
                case "quarters":
                    machine.setQuarters(machine.getQuarters() - coinCalcResult.getCoinsUsed());
                    break;

                case "dimes":
                    machine.setDimes(machine.getDimes() - coinCalcResult.getCoinsUsed());
                    break;

                case "nickels":
                    machine.setNickels(machine.getNickels() - coinCalcResult.getCoinsUsed());
                    break;

                case "pennies":
                    machine.setPennies(machine.getPennies() - coinCalcResult.getCoinsUsed());
                    break;
            }
        }

        machineDAO.save(machine);
    }

    private ChangeMoneyResponse buildChangeMoneyResponse(Double sumTotalMoney, Double totalMoneyAcountedFor, List<CoinCalcResult> coinCalcResults) {
        ChangeMoneyResponse response = new ChangeMoneyResponse();

        for (CoinCalcResult coinCalcResult : coinCalcResults) {
            switch (coinCalcResult.getCoinType()) {
                case "quarters":
                    response.setQuarters(coinCalcResult.getCoinsUsed());
                    break;

                case "dimes":
                    response.setDimes(coinCalcResult.getCoinsUsed());
                    break;

                case "nickels":
                    response.setNickels(coinCalcResult.getCoinsUsed());
                    break;

                case "pennies":
                    response.setPennies(coinCalcResult.getCoinsUsed());
                    break;
            }
        }

        return response;
    }
}
