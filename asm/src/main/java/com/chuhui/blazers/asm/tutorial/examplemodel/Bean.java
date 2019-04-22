package com.chuhui.blazers.asm.tutorial.examplemodel;

/**
 * Bean
 *
 * @author: 纯阳子
 * @Date: 2019/4/12
 * @Description:TODO
 */
public class Bean {

    private int f;
    public int getF() {
        return this.f;
    }
    public void setF(int f) {
        this.f = f;
    }

    public void checkAndSetF(int f) {
        if (f >= 0) {
            this.f = f;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
