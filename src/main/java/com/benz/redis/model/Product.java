package com.benz.redis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable{

    private static final long serialVersionUID = 5476796893430623702L;

    private int productId;
    private String name;
    private int qty;
    private double price;
}
