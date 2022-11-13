package com.api.apiresttest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    
    private String date;
    private String url;
    private String urlPhoto;
    private String title;
    private String summary;
    private String photoContent;
    private String photoContentType;

}
