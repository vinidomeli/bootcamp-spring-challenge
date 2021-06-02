package com.bootcamp.spring.challenge.domain.entity.activity;

import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_follows")
public class ActivityFollowsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "source_user", nullable = false)
    private UserEntity sourceUser;

    @ManyToOne
    @JoinColumn(name = "target_user", nullable = false)
    private UserEntity targetUser;
}
