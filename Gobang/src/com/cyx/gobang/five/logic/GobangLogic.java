package com.cyx.gobang.five.logic;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.cyx.gobang.five.chessboard.ChessBoard;
import com.cyx.gobang.five.constant.GobangConstant;
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
	checkWin(cBoard, dropPoint);
	return true;
    }
    /**
     * 落子点dropPoint 落子之后其周围的子的分数变更
     * @param cBoard
     * @param dropPoint
     */
    private void calculateScore(ChessBoard cBoard, ChessPoint dropPoint) {
	ChessPoint check = null;
	for (int i = -4; i < 5; i++) {
	    check = createNewCPoint(dropPoint, 0, i);//从左到右
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check)) {
		calculateHorizontalScore(cBoard, check);
	    }
	    
	    check = createNewCPoint(dropPoint, i, 0);//从上到下
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check)) {
		calculateVerticalScore(cBoard, check);
	    }
	    
	    check = createNewCPoint(dropPoint, i, i);//左上到右下
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check)) {
		calculateTopLeftScore(cBoard, check);
	    }
	    check = createNewCPoint(dropPoint, i, -i);//右上到左下
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check)) {
		calculateTopRightScore(cBoard, check);
	    }
	}
	findBetterByScore(cBoard);
    }
    
    private void checkWin(ChessBoard cBoard, ChessPoint dropPoint) {
	int coun = 0;
	ChessPoint check = null;
    	for(int i = 4; i > -5 ; i--){
    	    check = createNewCPoint(dropPoint, 0, i);//从左到右
    		if(checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check)){
    			coun++;
    			if(coun >= 5){
    	    		mWinFlag = isDrop;
    	    		return true;
    	    	}
    		}else{
    			coun = 0;
    		}
    	}
    }

    /**
     * 通过全盘的分数找到最高的12个点
     * @return 
     */
    private List<BestPoint> findBetterByScore(ChessBoard cBoard){
	ChessPointMsg[][] chessPointMsgs = cBoard.getForecastPointMsg().getChessPointMsgs();
	List<BestPoint> betterPoints = cBoard.getForecastPointMsg().getBetterPoints();
	betterPoints.clear();
	for(int i = 0 ; i < chessPointMsgs.length ; i++){
	    for(int j = 0 ; j < chessPointMsgs[i].length ; j++){
		if(chessPointMsgs[i][j].getPlayer().compare(ChessPlayer.BLANK)){
		    BestPoint bp = new BestPoint();
		    bp.setPoint(chessPointMsgs[i][j].getPoint());
		    bp.setBlackScoreAll(chessPointMsgs[i][j].getBlackScore().getScoreAll());
		    bp.setWhiteScoreAll(chessPointMsgs[i][j].getWhiteScore().getScoreAll());
		    betterPoints.add(bp);
		}
	    }
	}
	int maxSize = cBoard.getForecastPointMsg().getPointSize();
	Collections.sort(betterPoints);
	while(betterPoints.size() > maxSize){
	    betterPoints.remove(betterPoints.size() - 1);
	}
	return betterPoints;
    }
    
    private void calculateTopRightScore(ChessBoard cBoard, ChessPoint check) {
	String blackTopRightString = getTopRightString(cBoard, check, ChessPlayer.BLACK);
	int blackTopRightScore = getScoreByString(blackTopRightString);
	String whiteTopRightString = getTopRightString(cBoard, check, ChessPlayer.WHITE);
	int whiteTopRightScore = getScoreByString(whiteTopRightString);
	ChessPointMsg chessPointMsg = cBoard.getForecastPointMsg().getDropChessMsg(check);
	chessPointMsg.getBlackScore().setVerticalScore(blackTopRightScore);
	chessPointMsg.getWhiteScore().setVerticalScore(whiteTopRightScore);
    }

    private String getTopRightString(ChessBoard cBoard, ChessPoint check, ChessPlayer cPlayer) {
	ChessPoint check2 = null;
	StringBuffer sb = new StringBuffer();
	for (int i = -4; i < 5; i++) {
	    check2 = createNewCPoint(check, i, -i);//右上到左下
	    if(checkPointIsDrop(cBoard, check2)){
		sb.append("0");
	    }else if(checkPointIsDrop(cBoard, check2, cPlayer)){
		sb.append("1");
	    }else {
		sb.append("2");
	    }
	}
	sb.append("2").insert(0, "2");
	return sb.toString();
    }
    
    private void calculateTopLeftScore(ChessBoard cBoard, ChessPoint check) {
	String blackTopLeftString = getTopLeftString(cBoard, check, ChessPlayer.BLACK);
	int blackTopLeftScore = getScoreByString(blackTopLeftString);
	String whiteTopLeftString = getVerticalString(cBoard, check, ChessPlayer.WHITE);
	int whiteTopLeftScore = getScoreByString(whiteTopLeftString);
	ChessPointMsg chessPointMsg = cBoard.getForecastPointMsg().getDropChessMsg(check);
	chessPointMsg.getBlackScore().setVerticalScore(blackTopLeftScore);
	chessPointMsg.getWhiteScore().setVerticalScore(whiteTopLeftScore);
    }
    
    private String getTopLeftString(ChessBoard cBoard, ChessPoint check, ChessPlayer cPlayer) {
	ChessPoint check2 = null;
	StringBuffer sb = new StringBuffer();
	for (int i = -4; i < 5; i++) {
	    check2 = createNewCPoint(check, i, i);//左上到右下
	    if(checkPointIsDrop(cBoard, check2)){
		sb.append("0");
	    }else if(checkPointIsDrop(cBoard, check2, cPlayer)){
		sb.append("1");
	    }else {
		sb.append("2");
	    }
	}
	sb.append("2").insert(0, "2");
	return sb.toString();
    }
    
    private void calculateVerticalScore(ChessBoard cBoard, ChessPoint check) {
	String blackVerticalString = getVerticalString(cBoard, check, ChessPlayer.BLACK);
	int blackVerticalScore = getScoreByString(blackVerticalString);
	String whiteVerticalString = getVerticalString(cBoard, check, ChessPlayer.WHITE);
	int whiteVerticalScore = getScoreByString(whiteVerticalString);
	ChessPointMsg chessPointMsg = cBoard.getForecastPointMsg().getDropChessMsg(check);
	chessPointMsg.getBlackScore().setVerticalScore(blackVerticalScore);
	chessPointMsg.getWhiteScore().setVerticalScore(whiteVerticalScore);
    }
	

    private String getVerticalString(ChessBoard cBoard, ChessPoint check, ChessPlayer cPlayer) {
	ChessPoint check2 = null;
	StringBuffer sb = new StringBuffer();
	for (int i = -4; i < 5; i++) {
	    check2 = createNewCPoint(check, i, 0);//从上到下
	    if(checkPointIsDrop(cBoard, check2)){
		sb.append("0");
	    }else if(checkPointIsDrop(cBoard, check2, cPlayer)){
		sb.append("1");
	    }else {
		sb.append("2");
	    }
	}
	sb.append("2").insert(0, "2");
	return sb.toString();
    }

    private void calculateHorizontalScore(ChessBoard cBoard, ChessPoint check) {
	String blackHorizontalString = getHorizontalString(cBoard, check, ChessPlayer.BLACK);
	int blackHorizontalScore = getScoreByString(blackHorizontalString);
	String whiteHorizontalString = getHorizontalString(cBoard, check, ChessPlayer.WHITE);
	int whiteHorizontalScore = getScoreByString(whiteHorizontalString);
	ChessPointMsg chessPointMsg = cBoard.getForecastPointMsg().getDropChessMsg(check);
	chessPointMsg.getBlackScore().setHorizontalScore(blackHorizontalScore);
	chessPointMsg.getWhiteScore().setHorizontalScore(whiteHorizontalScore);
    }

    private String getHorizontalString(ChessBoard cBoard, ChessPoint check, ChessPlayer cPlayer) {
	ChessPoint check2 = null;
	StringBuffer sb = new StringBuffer();
	for (int i = -4; i < 5; i++) {
	    check2 = createNewCPoint(check, 0, i);//从左到右
	    if(checkPointIsDrop(cBoard, check2)){
		sb.append("0");
	    }else if(checkPointIsDrop(cBoard, check2, cPlayer)){
		sb.append("1");
	    }else {
		sb.append("2");
	    }
	}
	sb.append("2").insert(0, "2");
	return sb.toString();
    }
    
    private int getScoreByString(String str) {
	StringBuffer sb = new StringBuffer(str);
	int length = sb.length();
	int maxScore = 0;
	for(int i = 0 ; i < length - 6 ; i++){
	    String substring = sb.substring(i,i+6);
	    if(GobangConstant.map.containsKey(substring)){
		maxScore = Math.max(maxScore, GobangConstant.map.get(substring));
	    }
	}
	return maxScore;
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
	StringBuffer sb = new StringBuffer("123456789");
	String substring = sb.substring(0, 6);
	System.out.println(substring);
    }
}
