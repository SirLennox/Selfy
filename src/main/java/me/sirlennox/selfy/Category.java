package me.sirlennox.selfy;

public enum Category {
    FUN("Fun"), OTHER("Other"), UTIL("Utility"), TROLL("Troll");

    public String name;
    Category(String name) {
        this.name = name;
    }
}
