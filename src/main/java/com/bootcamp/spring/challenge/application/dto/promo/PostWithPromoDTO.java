package com.bootcamp.spring.challenge.application.dto.promo;

import com.bootcamp.spring.challenge.application.dto.post.PostResponseDTO;
import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;
import com.bootcamp.spring.challenge.domain.entity.promo.PromoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostWithPromoDTO extends PostResponseDTO {
    private PromoResponseDTO promo;

    public static PostWithPromoDTO toPostWithPromo(final PromoEntity promoEntity) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<PromoEntity, PostWithPromoDTO> propertyMap = new PropertyMap<PromoEntity, PostWithPromoDTO>() {
            @Override
            protected void configure() {
                map().setProduct(ProductResponseDTO.toResponseDTO(source.getPost().getProductId()));
                map().setPostId(source.getPost().getId());
                map().setUserId(source.getPost().getUserId().getId());
                map().setPrice(source.getPost().getPrice());
                map().setCreatedAt(source.getPost().getCreatedAt());
                map().getPromo().setId(source.getId());
                map().getPromo().setDiscount(source.getDiscount());
                map().getPromo().setCreatedAt(source.getCreatedAt());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(promoEntity, PostWithPromoDTO.class);
    }
}
