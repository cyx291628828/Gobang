package com.cyx.gobang.five.structs;

public class BestPoint {
    /**
     * @expression 该点所在的X轴Y轴的位置
     */
    private ChessPoint point;
    /**
     * @expression 该点黑棋的总分数
     */
    private int blackScoreAll;
    /**
     * @expression 该点白棋的总分数
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
