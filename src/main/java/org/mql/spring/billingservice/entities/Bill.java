package org.mql.spring.billingservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long customerId;

    @NonNull
    private Date billDate;

    @Transient
    private Customer customer;

    @OneToMany(fetch =FetchType.LAZY ,mappedBy = "bill")
    private Collection<ProductItem> productItems;
}
