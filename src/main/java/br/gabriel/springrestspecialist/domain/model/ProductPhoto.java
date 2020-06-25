package br.gabriel.springrestspecialist.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_product_photos")
public class ProductPhoto {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id")
    private Integer id;
    
    private String filename;
    
    private String description;
    
    private String contentType;
    
    private Integer size;   
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;
}
