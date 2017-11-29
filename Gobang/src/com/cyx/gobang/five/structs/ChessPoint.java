package com.cyx.gobang.five.structs;

/**
 * 
 * @author 陈宇轩
 * @date 2017年11月14
 * @function 棋盘上每个已落子点的坐标
 *
 */
public class ChessPoint implements Cloneable{
    /**
     * @expression 该点所在的X轴的位置
     */
    private int point_x;
    /**
     * @expression 该点所在的Y轴的位置
     */
    private int point_y;

    public ChessPoint(int point_x, int point_y) {
	this.point_x = point_x;
	this.point_y = point_y;
    }

    public int getPoint_x() {
	return point_x;
    }

    public void setPoint_x(int point_x) {
	this.point_x = point_x;
    }

    public int getPoint_y() {
	return point_y;
    }

    public void setPoint_y(int point_y) {
	this.point_y = point_y;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
