package br.gabriel.springrestspecialist.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilter {
    private Integer userId;
    
    private Integer restaurantId;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime initialDate;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime endingDate;
}
