package com.example.bookstore.api.model.request;

import com.example.bookstore.config.ValidationConfig;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class UpdateBookRequest extends BasicRequest{

    @NotBlank(message = "{bookName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_BOOK_NAME, message = "{bookName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{name.pattern}")
    private String name;

    @PositiveOrZero(message = "{price.positiveOrZero}")
    private Double price;

    @Positive(message = "{publishedYear.positive}")
    @Max(value = 2100, message = "{publishedYear.max}")
    private Integer publishedYear;

    private List<Integer> authorIds;

    @NotBlank(message = "{category.notBlank}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME)
    @Size(max = ValidationConfig.MAX_LENGTH_NAME)
    private String category;

    @NotBlank(message = "{description.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT)
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT)
    private String description;

    public UpdateBookRequest() {
    }

    public UpdateBookRequest(Integer id, String name, Double price,
                             Integer publishedYear, List<Integer> authorIds,
                             String category, String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.publishedYear = publishedYear;
        this.authorIds = authorIds;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "UpdateBookRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", publishedYear=" + publishedYear +
                ", authorIds=" + authorIds +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
