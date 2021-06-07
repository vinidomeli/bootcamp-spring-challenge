package com.bootcamp.spring.challenge.application.dto.post;

import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {
    private UUID user;

    private Integer productId;

    private Double price;

    public static ActivityPostEntity toPostEntity(final PostRequestDTO postRequestDTO) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<PostRequestDTO, ActivityPostEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                final LocalDate now = LocalDate.now();

                map().setCreatedAt(now);
                map().getProductId().setId(source.getProductId());
                map().getUserId().setId(source.getUser());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(postRequestDTO, ActivityPostEntity.class);
    }

}
