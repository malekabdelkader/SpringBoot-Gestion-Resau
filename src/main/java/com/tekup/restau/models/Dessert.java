package com.tekup.restau.models;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Dessert")
public class Dessert extends Met {


}