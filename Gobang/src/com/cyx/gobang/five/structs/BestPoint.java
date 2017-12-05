package com.cyx.gobang.five.structs;

public class BestPoint implements Comparable<BestPoint>{
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
    
    public int getMaxScore(){
	return whiteScoreAll > blackScoreAll ? whiteScoreAll : blackScoreAll;
    }

    /**
     * �����
     */
    @Override
    public int compareTo(BestPoint bestPoint) {
	if (bestPoint.getMaxScore() > this.getMaxScore()) {
	    return 1;
	} else if (bestPoint.getMaxScore() < this.getMaxScore()) {
	    return -1;
	} else {
	    return 0;
	}
    }

}
