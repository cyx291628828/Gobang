package com.cyx.gobang.five.structs;

public class BestPoint {
    /**
     * @expression �õ����ڵ�X��Y���λ��
     */
    private ChessPoint point;
    /**
     * @expression �õ������ܷ���
     */
    private int blackScoreAll;
    /**
     * @expression �õ������ܷ���
     */
    private int whiteScoreAll;

    public ChessPoint getPoint() {
	return point;
    }

    public void setPoint(ChessPoint point) {
	this.point = point;
    }

    public int getBlackScoreAll() {
	return blackScoreAll;
    }

    public void setBlackScoreAll(int blackScoreAll) {
	this.blackScoreAll = blackScoreAll;
    }

    public int getWhiteScoreAll() {
	return whiteScoreAll;
    }

    public void setWhiteScoreAll(int whiteScoreAll) {
	this.whiteScoreAll = whiteScoreAll;
    }

}
