package com.cyx.gobang.five.structs;

/**
 * 棋盘信息节点。用来存落子记录，和预测的记录，二叉树的形势存
 * 左孩子为子节点（下一步）  右孩子为兄弟节点（当前部的领一个较好的落子点）
 * @author 小轩轩
 *
 */
public class ChessBoardNode {
    
    ChessPointMsg record = null;
    ForecastPointMsg fPointMsg = null;
    
    ChessBoardNode TopNode = null;
    ChessBoardNode leftNode = null;
    ChessBoardNode rightNode = null;
    
    public ChessBoardNode(ChessPointMsg record , ForecastPointMsg fPointMsg){
	try {
	    this.record = record.clone();
	    this.fPointMsg = fPointMsg.clone();
	} catch (CloneNotSupportedException e) {
	    e.printStackTrace();
	}
    }
    
    public ChessBoardNode(){
	
    }

    public ChessPointMsg getRecord() {
        return record;
    }

    public void setRecord(ChessPointMsg record) {
        this.record = record;
    }

    public ForecastPointMsg getfPointMsg() {
        return fPointMsg;
    }

    public void setfPointMsg(ForecastPointMsg fPointMsg) {
        this.fPointMsg = fPointMsg;
    }

    public ChessBoardNode getTopNode() {
        return TopNode;
    }

    public void setTopNode(ChessBoardNode topNode) {
        TopNode = topNode;
    }

    public ChessBoardNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(ChessBoardNode leftNode) {
        this.leftNode = leftNode;
    }

    public ChessBoardNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(ChessBoardNode rightNode) {
        this.rightNode = rightNode;
    }
    
    
}
