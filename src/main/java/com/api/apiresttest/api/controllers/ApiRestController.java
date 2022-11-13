package com.api.apiresttest.api.controllers;

import java.util.List;
import com.api.apiresttest.api.dtos.ResponseDto;

public interface ApiRestController {
    public List<ResponseDto> getData(String searchData, boolean returnPhoto) throws Exception;

}
