package com.bootcamp.spring.challenge.domain.entity.promo;

import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_promo")
public class PromoEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private ActivityPostEntity post;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "active", nullable = false)
    private Boolean active;
}