package br.gabriel.springrestspecialist.domain.model.aggregate;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DailySales {
    private Date date;
    
    private Long totalSold;
    
    private BigDecimal totalEarned;
}
