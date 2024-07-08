package com.example.shoppingserver.domain.item.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
    private String expirationDate;
    private ArrayList<String> nutrientList;
}
