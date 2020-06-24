package br.gabriel.springrestspecialist.domain.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DailySales {
    private LocalDate date;
    
    private Integer totalSold;
    
    private BigDecimal totalEarned;
}
