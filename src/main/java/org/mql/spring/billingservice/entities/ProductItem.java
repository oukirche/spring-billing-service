package org.mql.spring.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long productId;

    @Transient
    protected Product product;

    @NonNull
    private double quantity;

    @NonNull
    private double price;

    @ManyToOne
    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
}
