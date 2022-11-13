package com.api.apiresttest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import com.api.apiresttest.api.services.ApiRestService;

import com.api.apiresttest.api.dtos.ResponseDto;
import java.util.List;


@RestController("ApiRestController")
@RequestMapping("/api")
public class ApiRestControllerImpl implements ApiRestController{

    @Autowired
    private ApiRestService apiRestService;
    
    @Override
    @GetMapping(value = "/consulta")
    public List<ResponseDto> getData(
            @RequestParam(value = "q", required = false) String searchData,
            @RequestParam(value = "f", required = false) boolean returnPhoto
        ) throws Exception {
        
        return apiRestService.getData(searchData, returnPhoto);
    }

}
