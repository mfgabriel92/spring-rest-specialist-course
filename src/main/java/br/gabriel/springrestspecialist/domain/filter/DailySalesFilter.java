package br.gabriel.springrestspecialist.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DailySalesFilter {
    @ApiParam(value = "The restaurant ID", example = "1")
    private Integer restaurantId;
    
    @ApiParam(value = "The initial date of the filtering", example = "2020-06-01T01:51:57Z")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime initialDate;
    
    @ApiParam(value = "The ending date of the filtering", example = "2020-06-30T01:51:57Z")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime endingDate;
}
