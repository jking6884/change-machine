package com.example.changeapp.controller;

import com.example.changeapp.domain.request.AddMoneyRequest;
import com.example.changeapp.domain.response.ManageMoneyResponse;
import com.example.changeapp.service.ManageMoneyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class ManageMachineController {

    private ManageMoneyService manageMoneyService;

    @Autowired
    public ManageMachineController(ManageMoneyService manageMoneyService) {
        this.manageMoneyService = manageMoneyService;
    }

    /**
     * Endpoint to add money to the machine
     */
    @ApiOperation("Machine Management API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 204, message = "Unsuccessful"),
            @ApiResponse(code = 500, message = "Error")
    })
    @RequestMapping(value = "/machine/add-money", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> addMoneyToMachine(@RequestBody AddMoneyRequest addMoneyRequest) {
        ManageMoneyResponse manageMoneyResponse = manageMoneyService.addMoney(addMoneyRequest);

        if (manageMoneyResponse != null) {
            return new ResponseEntity<>("Successfully added money", HttpStatus.CREATED);
        } else {
            throw new RuntimeException("Request could not be processed at this time");
        }
    }
}
