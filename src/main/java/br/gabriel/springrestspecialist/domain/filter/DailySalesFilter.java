package br.gabriel.springrestspecialist.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DailySalesFilter {
    private Integer restaurantId;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime initialDate;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime endingDate;
}
