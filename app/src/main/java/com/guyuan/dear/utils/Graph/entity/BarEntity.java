package com.guyuan.dear.utils.Graph.entity;

/**
 * Created by TL
 * on 2020/1/15
 */
public class BarEntity {
    private String name;
    private int number;
    private int color= -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public BarEntity(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public BarEntity(String name, int number, int color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

    public BarEntity() {
    }
}
