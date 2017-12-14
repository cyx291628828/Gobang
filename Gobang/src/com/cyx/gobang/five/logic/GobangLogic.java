package com.cyx.gobang.five.logic;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cyx.gobang.five.chessboard.ChessBoard;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.structs.BestPoint;
import com.cyx.gobang.five.structs.ChessPoint;
import com.cyx.gobang.five.structs.ChessPointMsg;

public class GobangLogic {
    private static volatile GobangLogic gobangLogic;
    private static final Object S_OBJECT = new Object();
    
    public static GobangLogic getMe(){
	if(gobangLogic == null){
	    synchronized(S_OBJECT){
		if(gobangLogic == null){
		    gobangLogic = new GobangLogic();
		}
	    }
	}
	return gobangLogic;
    }
    
    private GobangLogic() {
	
    }
    
    /**
     * 检验 落子 算分
     * @param cBoard
     * @param cPoint
     * @return
     */
    public boolean dropChess(ChessBoard cBoard, ChessPoint cPoint){
	if(!checkChessIsDrop(cBoard, cPoint,ChessPlayer.BLANK)){
	    
	}
	return false;
    }
    /**
     * 第三个参数为null默认检验是否为空位置
     * @param cBoard
     * @param cPoint
     * @return
     */
    private boolean checkChessIsDrop(ChessBoard cBoard, ChessPoint cPoint) {
	return checkChessIsDrop(cBoard, cPoint,ChessPlayer.BLANK);
    }
    private boolean checkChessIsDrop(ChessBoard cBoard, ChessPoint cPoint, ChessPlayer cPlayer) {
	if(cPlayer == null){
	    cPlayer = ChessPlayer.BLANK;
	}
	ChessPointMsg[][] chessPointMsg = cBoard.getChessPointMsg();
	ChessPlayer player = chessPointMsg[cPoint.getPoint_x()][cPoint.getPoint_y()].getPlayer();
	if(player.compare(cPlayer)){
	    return true;
	}
	return false;
    }
    @Test
    private String listToJson(ChessBoard cBoard){
	String jsonString = JSON.toJSONString(cBoard.getRecordList());
	cBoard.getRecordList();
	return "";
    }
    
    public static void main(String[] args) {
	ChessBoard cbBoard = new ChessBoard();
	BestPoint bPoint = new BestPoint();
	cbBoard.getBetterPoints().add(bPoint);
	bPoint.setPoint(new ChessPoint(2, 3));
	String jsonString = JSON.toJSONString(cbBoard.getBetterPoints());
	System.err.println(jsonString);
    }
}
