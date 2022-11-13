package com.api.apiresttest.api.services;

import com.api.apiresttest.api.dtos.ResponseDto;
import java.util.List;

public interface ApiRestService {
    public List<ResponseDto> getData(String searchData, boolean returnPhoto) throws Exception;
}
