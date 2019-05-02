package com.example.changeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Integer pennies;
    public Integer nickels;
    public Integer dimes;
    public Integer quarters;

    public Integer getCoinCountByString(String name) {
        try {
            return (int) getClass().getField(name).get(this);
        } catch (Exception ex) {
            throw new RuntimeException("(" + name + ") field does not exist in Machine class");
        }
    }
}
