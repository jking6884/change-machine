package com.example.changeapp.controller;

import com.example.changeapp.domain.request.ChangeMoneyRequest;
import com.example.changeapp.domain.response.ChangeMoneyResponse;
import com.example.changeapp.service.ChangeMoneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class ChangeController {

    private ChangeMoneyService changeMoneyService;

    @Autowired
    public ChangeController(ChangeMoneyService changeMoneyService) {
        this.changeMoneyService = changeMoneyService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 204, message = "Unsuccessful"),
            @ApiResponse(code = 500, message = "Error")
    })
    @RequestMapping(value = "/change-money", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ChangeMoneyResponse changeMoney(@RequestBody ChangeMoneyRequest changeMoneyRequest) {
        return changeMoneyService.changeMoney(changeMoneyRequest);
    }
}
