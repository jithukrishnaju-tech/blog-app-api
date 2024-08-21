package com.krishnaintech.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "Category name is empty")
    private String categoryName;
    @NotEmpty(message = "Category detail is empty")
    private String categoryDetail;
}
