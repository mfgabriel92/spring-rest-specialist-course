package br.gabriel.springrestspecialist.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilter {
    @ApiModelProperty(value = "The user ID", example = "1")
    private Integer userId;
    
    @ApiModelProperty(value = "The restaurant ID", example = "1")
    private Integer restaurantId;
    
    @ApiModelProperty(value = "The initial date", example = "2020-07-07T00:00:00Z")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime initialDate;
    
    @ApiModelProperty(value = "The ending date", example = "2020-07-08T00:00:00Z")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime endingDate;
}
