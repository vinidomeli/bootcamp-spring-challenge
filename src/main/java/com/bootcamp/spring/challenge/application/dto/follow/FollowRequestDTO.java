package com.bootcamp.spring.challenge.application.dto.follow;

import com.bootcamp.spring.challenge.domain.entity.activity.ActivityFollowsEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowRequestDTO {
    @NotNull
    private UUID source;

    @NotNull
    private UUID target;

    public static ActivityFollowsEntity toEntity(final FollowRequestDTO requestDTO) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<FollowRequestDTO, ActivityFollowsEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().getSourceUser().setId(source.getSource());
                map().getTargetUser().setId(source.getTarget());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(requestDTO, ActivityFollowsEntity.class);
    }
}
