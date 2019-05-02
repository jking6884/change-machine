package com.example.changeapp.service;

import com.example.changeapp.domain.request.AddMoneyRequest;
import com.example.changeapp.dao.MachineDAO;
import com.example.changeapp.domain.response.ChangeMoneyResponse;
import com.example.changeapp.domain.response.ManageMoneyResponse;
import com.example.changeapp.entity.Machine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManageMoneyService {

    private MachineDAO machineDAO;

    @Autowired
    public ManageMoneyService(MachineDAO machineDAO) {
        this.machineDAO = machineDAO;
    }

    public ManageMoneyResponse addMoney(AddMoneyRequest addMoneyRequest) {
        try {
            Machine machine = machineDAO.findById(addMoneyRequest.getId().longValue()).get();
            machine.setPennies(machine.getPennies() + addMoneyRequest.getPennies());
            machine.setNickels(machine.getNickels() + addMoneyRequest.getNickels());
            machine.setDimes(machine.getDimes() + addMoneyRequest.getDimes());
            machine.setQuarters(machine.getQuarters() + addMoneyRequest.getQuarters());
            machineDAO.save(machine);

            return ManageMoneyResponse.builder()
                    .quarters(machine.getQuarters())
                    .dimes(machine.getDimes())
                    .nickels(machine.getNickels())
                    .pennies(machine.getPennies())
                    .build();
        } catch (Exception ex) {
            log.error("No machine found with ID");
            throw new RuntimeException("No machine found");
        }
    }
}
