package org.example.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;
import org.example.model.Category;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder

public class ProductDTO {

    private Long id;
    private String name;
}
