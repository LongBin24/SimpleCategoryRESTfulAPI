package co.istard.productapisimpledemo.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private  Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer userId;//User id
}
