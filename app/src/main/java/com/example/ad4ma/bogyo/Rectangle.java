package com.example.ad4ma.bogyo;

/**
 * Created by ad4ma on 2017. 04. 22..
 */

class Rectangle {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}
