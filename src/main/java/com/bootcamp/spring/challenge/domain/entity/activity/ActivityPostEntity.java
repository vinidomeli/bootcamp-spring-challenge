package com.bootcamp.spring.challenge.domain.entity.activity;

import com.bootcamp.spring.challenge.domain.entity.product.ProductEntity;
import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_post")
public class ActivityPostEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productId;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "price", nullable = false)
    private Double price;
}
