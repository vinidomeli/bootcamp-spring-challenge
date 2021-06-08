package com.bootcamp.spring.challenge.application.repository;

import com.bootcamp.spring.challenge.domain.entity.promo.PromoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PromoRepository extends JpaRepository<PromoEntity, UUID> {

    public List<PromoEntity> findAllByPostUserIdIdAndActive(UUID userId, Boolean active);

    @Modifying
    @Transactional
    @Query("update PromoEntity p set p.active = false where p.id = ?1")
    public Integer disablePromo(UUID promoId);

    public Optional<PromoEntity> findByPostIdAndActive(UUID postId, Boolean active);

    @Query("select a,p from ActivityPostEntity a join PromoEntity p on a.id = p.post.id where a.userId.id = ?1 and p.active=true")
    public List<PromoEntity> findAllPromoPostsByUser(UUID userId);
}
