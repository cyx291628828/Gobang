package com.cyx.gobang.five.structs;

/**
 * 
 * @author 陈宇轩
 * @date 2017年11月14
 * @function 棋盘上每个可落子点的各个方向的分数
 *
 */
public class ChessPointScore {
    /**
     * @expression 左上到右下的成绩
     */
    private int zuoScore;
    /**
     * @expression 右上到左下的成绩
     */
    private int youScore;
    /**
     * @expression 垂直方向上的成绩
     */
    private int verticalScore;
    /**
     * @expression 水平方向上的成绩
     */
    private int horizontalScore;
    /**
     * @expression 总成绩
     */
    private int scoreAll;
    
    public int getZuoScore() {
        return zuoScore;
    }
    public void setZuoScore(int zuoScore) {
	if(this.zuoScore != zuoScore){
	    this.zuoScore = zuoScore;
	    calculateScoreAll();
	}
    }
    public int getYouScore() {
        return youScore;
    }
    public void setYouScore(int youScore) {
	if(this.youScore != youScore){
	    this.youScore = youScore;
	    calculateScoreAll();
	}
    }
    public int getVerticalScore() {
        return verticalScore;
    }
    public void setVerticalScore(int verticalScore) {
        if(this.verticalScore != verticalScore){
            this.verticalScore = verticalScore;
            calculateScoreAll();
        }
    }
    public int getHorizontalScore() {
        return horizontalScore;
    }
    public void setHorizontalScore(int horizontalScore) {
        if(this.horizontalScore != horizontalScore){
            this.horizontalScore = horizontalScore;
            calculateScoreAll();
        }
    }
    public void setFourScore(int score) {
	this.horizontalScore = score;
	this.verticalScore = score;
	this.youScore = score;
	this.zuoScore = score;
	calculateScoreAll();
    }
    public int getScoreAll() {
        return scoreAll;
    }
    public void setScoreAll(int scoreAll) {
        this.scoreAll = scoreAll;
    }
    public void calculateScoreAll(){
	int scoreAll = zuoScore + youScore + verticalScore + horizontalScore;
	setScoreAll(scoreAll);
    }
    public void clear(){
	setHorizontalScore(0);
	setScoreAll(0);
	setVerticalScore(0);
	setYouScore(0);
	setZuoScore(0);
    }
}
