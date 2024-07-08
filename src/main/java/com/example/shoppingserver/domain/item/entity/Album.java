package com.example.shoppingserver.domain.item.entity;

import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.dto.response.AlbumResponse;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;

    @Override
    protected void setData(ItemRequest request) {
        artist = request.getAlbum().getArtist();
    }

    @Override
    public ItemResponse createItemResponse() {
        return setItemResponseBuilder()
                .album(new AlbumResponse(artist))
                .build();
    }
}
