package org.example.dto;

import lombok.*;
import org.example.model.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartRequestDto {
    private ProductDTO productDTO;
    private EmailDto email;
}
