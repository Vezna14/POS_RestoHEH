package be.heh.g2.application.port.in;
//import jakarta.validation.constraints.*;
import be.heh.g2.application.common.Validation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record InputProductValidator (
    @NotNull long id,
    @NotNull String name,

    @NotNull @Min(value = 0, message = "Price can not be negative")
    double price,

    @NotNull ArrayList<String> category,

    @NotNull @Min(value = 0, message = "Stock can not be negative") int stock,
    @NotNull String photo)
{
    public InputProductValidator(long id,String name,double price,ArrayList<String> category,int stock,String photo){
        this.id=id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock=stock;
        this.photo=photo;
        Validation.validate(this);


    }
}
