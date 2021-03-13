package ru.motrichkin.persistence;

import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ProductSpecification {

    private Specification<Product> productSpecification;
    private Integer minPrice;
    private Integer maxPrice;

    private ProductSpecification() {};

    public Specification<Product> getProductSpecification() {
        return productSpecification;
    }

    public Integer getMinPrice() {
        return minPrice != null ? minPrice : 0;
    }

    public Integer getMaxPrice() {
        return maxPrice != null ? maxPrice : Integer.MAX_VALUE;
    }

    public static Builder newBuilder() {
        return new ProductSpecification().new Builder();
    }

    public class Builder {

        private Builder() { }

        public Builder setMinPrice(Integer minPrice) {
            ProductSpecification.this.minPrice = minPrice;
            return this;
        }

        public Builder setMaxPrice(Integer maxPrice) {
            ProductSpecification.this.maxPrice = maxPrice;
            return this;
        }

        public ProductSpecification build() {
            ProductSpecification.this.productSpecification = (root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.between(root.get("cost"),
                    Optional.ofNullable(ProductSpecification.this.minPrice).orElse(0),
                    Optional.ofNullable(ProductSpecification.this.maxPrice).orElse(Integer.MAX_VALUE));
            return ProductSpecification.this;
        }

    }

}
