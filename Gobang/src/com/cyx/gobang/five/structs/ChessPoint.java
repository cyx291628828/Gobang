package com.cyx.gobang.five.structs;

/**
 * 
 * @author ������
 * @date 2017��11��14
 * @function ������ÿ�������ӵ������
 *
 */
public class ChessPoint implements Cloneable{
    /**
     * @expression �õ����ڵ�X���λ��
     */
    private int point_x;
    /**
     * @expression �õ����ڵ�Y���λ��
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
