package com.cyx.gobang.five.logic;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.cyx.gobang.five.chessboard.ChessBoard;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.structs.BestPoint;
import com.cyx.gobang.five.structs.ChessPoint;
import com.cyx.gobang.five.structs.ChessPointMsg;
import com.cyx.gobang.five.structs.ChessPointScore;
import com.cyx.gobang.five.structs.ForecastPointMsg;

public class GobangLogic {

    private static Logger logger = LogManager.getLogger(GobangLogic.class.getName());

    private static volatile GobangLogic gobangLogic;
    private static final Object S_OBJECT = new Object();

    public static GobangLogic getMe() {
	if (gobangLogic == null) {
	    synchronized (S_OBJECT) {
		if (gobangLogic == null) {
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
     * 
     * @param cBoard
     * @param dropPoint
     *            落子点
     * @param cPlayer
     *            落子方
     * @return
     */
    public boolean dropChess(ChessBoard cBoard, ChessPoint dropPoint, ChessPlayer cPlayer) {
	if (!checkPointIsDrop(cBoard, dropPoint, ChessPlayer.BLANK)) {
	    return false;
	}
	if (cBoard.getRecordList().size() == 0) {
	    cBoard.setPlayer(cPlayer);
	} else {
	    if (cBoard.getPlayer().compare(cPlayer)) {
		logger.error("下子错误:当前下子方与前一方相同");
		return false;
	    } else {
		cBoard.setPlayer(cPlayer);
	    }
	}
	ForecastPointMsg forecastPointMsg = cBoard.getForecastPointMsg();
	forecastPointMsg.addDropChess(dropPoint, cPlayer);
	// 计算分数
	calculateScore(cBoard, dropPoint);
	cBoard.getRecordList().add(forecastPointMsg.getDropChessMsg(dropPoint));
	return true;
    }
    /**
     * 落子点dropPoint 落子之后其周围的子的分数变更
     * @param cBoard
     * @param dropPoint
     */
    private void calculateScore(ChessBoard cBoard, ChessPoint dropPoint) {
	ChessPointScore blackScore ;
	ChessPointScore whiteScore ;
	ChessPoint check = null;
	for (int i = -4; i < 5; i++) {
	    check = createNewCPoint(dropPoint, 0, i);//从左到右
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check)) {
		getHorizontalScore(cBoard, check, dropPoint, ChessPlayer.BLACK);
	    }
	    check = createNewCPoint(dropPoint, i, 0);//从上到下
	    
	    check = createNewCPoint(dropPoint, i, i);//左上到右下
	    
	    check = createNewCPoint(dropPoint, i, -i);//右上到左下
	}
    }

    private void getHorizontalScore(ChessBoard cBoard, ChessPoint check, ChessPoint cPoint, ChessPlayer cPlayer) {
	//检测 check 与 cPoint 点之间（不考虑此check、cPoint两点）有无除了空和cPlayer的其他子，有不计算，没有就计算
	checkBetweenTwoPoints(cBoard, check, cPoint);
	
    }

    private void checkBetweenTwoPoints(ChessBoard cBoard, ChessPoint check, ChessPoint cPoint, ChessPlayer... cPlayer) {
	
    }

    private ChessPoint createNewCPoint(ChessPoint cPoint, int apart_x, int apart_y) {
	return new ChessPoint(cPoint.getPoint_x() + apart_x, cPoint.getPoint_y() + apart_y);
    }

    public boolean dropChess(ChessBoard cBoard, ChessPoint cPoint, int cMark) {
	return dropChess(cBoard, cPoint, ChessPlayer.formMark(cMark));
    }

    /**
     * 第三个参数为null默认检验是否为空位置
     * 
     * @param cBoard
     * @param cPoint
     * @return
     */
    public boolean checkPointIsDrop(ChessBoard cBoard, ChessPoint cPoint) {
	return checkPointIsDrop(cBoard, cPoint, ChessPlayer.BLANK);
    }

    /**
     * 检测cPoint点上落子方是否是cPlayer中的一个
     * 
     * @param cBoard
     * @param cPoint
     * @param cPlayer
     * @return
     */
    public boolean checkPointIsDrop(ChessBoard cBoard, ChessPoint cPoint, ChessPlayer... cPlayer) {
	if (cPlayer == null) {
	    cPlayer = new ChessPlayer[] { ChessPlayer.BLANK };
	}
	if (!checkChessPointIndex(cBoard, cPoint)) {
	    return false;
	}
	ChessPointMsg[][] chessPointMsg = cBoard.getForecastPointMsg().getChessPointMsgs();
	ChessPlayer player = chessPointMsg[cPoint.getPoint_x()][cPoint.getPoint_y()].getPlayer();
	for (ChessPlayer cp : cPlayer) {
	    if (player.compare(cp)) {
		return true;
	    }
	}
	return false;
    }

    public boolean checkChessPointIndex(ChessBoard cBoard, ChessPoint cPoint) {
	if (cPoint.getPoint_x() < 0 || cPoint.getPoint_y() < 0) {
	    return false;
	}
	ChessPointMsg[][] chessPointMsg = cBoard.getForecastPointMsg().getChessPointMsgs();
	if (cPoint.getPoint_x() >= chessPointMsg.length) {
	    return false;
	}
	if (cPoint.getPoint_y() >= chessPointMsg.length) {
	    return false;
	}
	return true;
    }

    private String listToJson(ChessBoard cBoard) {
	String jsonString = JSON.toJSONString(cBoard.getRecordList());
	cBoard.getRecordList();
	return "";
    }

    public static void main(String[] args) {
	logger.error("哈哈");
	ChessBoard cbBoard = new ChessBoard();
	BestPoint bPoint = new BestPoint();
	cbBoard.getForecastPointMsg().getBetterPoints().add(bPoint);
	bPoint.setPoint(new ChessPoint(2, 3));
	String jsonString = JSON.toJSONString(cbBoard.getForecastPointMsg().getBetterPoints());
	System.err.println((new Date()).getTime());
    }
}
