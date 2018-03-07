package com.cyx.gobang.five.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.cyx.gobang.five.Utils.RandomUtils;
import com.cyx.gobang.five.chessboard.ChessBoard;
import com.cyx.gobang.five.constant.GobangConstant;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.enums.GameState;
import com.cyx.gobang.five.structs.BestPoint;
import com.cyx.gobang.five.structs.ChessPoint;
import com.cyx.gobang.five.structs.ChessPointMsg;
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
	ChessPlayer checkWin = checkWin(cBoard, dropPoint);
	if(!checkWin.compare(ChessPlayer.BLANK)){
	    System.out.println(checkWin.getDes()+"赢");
	}
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
    
    public ChessPlayer checkWin(ChessBoard cBoard, ChessPoint dropPoint) {
	return checkWin(cBoard, dropPoint, cBoard.getPlayer());
    }
    public ChessPlayer checkWin(ChessBoard cBoard, ChessPoint dropPoint, ChessPlayer checkPlayer) {
	if(checkPlayer.compare(ChessPlayer.BLANK)){
	    return ChessPlayer.BLANK;
	}
	int coun = 0;
	ChessPoint check = null;
	for (int i = -4; i < 5; i++) {
	    check = createNewCPoint(dropPoint, 0, i);// 从左到右
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check, checkPlayer)) {
		coun++;
		if (coun >= 5) {
		    //改变游戏状态
		    cBoard.upState(GameState.OVER);
		    cBoard.getGobangGameOver().onGameWin(cBoard, checkPlayer);
		    return checkPlayer;
		}
	    } else {
		coun = 0;
	    }
	}
	
	coun = 0;
	for (int i = -4; i < 5; i++) {
	    check = createNewCPoint(dropPoint, i, 0);// 从上到下
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check, checkPlayer)) {
		coun++;
		if (coun >= 5) {
		    //改变游戏状态
		    cBoard.upState(GameState.OVER);
		    cBoard.getGobangGameOver().onGameWin(cBoard, checkPlayer);
		    return checkPlayer;
		}
	    } else {
		coun = 0;
	    }
	}
	
	coun = 0;
	for (int i = -4; i < 5; i++) {
	    check = createNewCPoint(dropPoint, i, i);// 左上到右下
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check, checkPlayer)) {
		coun++;
		if (coun >= 5) {
		    //改变游戏状态
		    cBoard.upState(GameState.OVER);
		    cBoard.getGobangGameOver().onGameWin(cBoard, checkPlayer);
		    return checkPlayer;
		}
	    } else {
		coun = 0;
	    }
	}
	
	coun = 0;
	for (int i = -4; i < 5; i++) {
	    check = createNewCPoint(dropPoint, i, -i);// 右上到左下
	    if (checkChessPointIndex(cBoard, check) && checkPointIsDrop(cBoard, check, checkPlayer)) {
		coun++;
		if (coun >= 5) {
		    //改变游戏状态
		    cBoard.upState(GameState.OVER);
		    cBoard.getGobangGameOver().onGameWin(cBoard, checkPlayer);
		    return checkPlayer;
		}
	    } else {
		coun = 0;
	    }
	}
	
	return ChessPlayer.BLANK;
    }

    /**
     * 通过全盘的分数找到最高的点集合
     * 重新全盘计算 先清除再计算
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
    
    public BestPoint getBestPointByIQ(ChessBoard cBoard){
	return getBestPointByIQ(cBoard, cBoard.getCOMPUTERIQ(), false);
    }
    public BestPoint getBestPointByIQ(ChessBoard cBoard, boolean calculate){
	return getBestPointByIQ(cBoard, cBoard.getCOMPUTERIQ(), calculate);
    }
    public BestPoint getBestPointByIQ(ChessBoard cBoard, int IQ){
	return getBestPointByIQ(cBoard, IQ, false);
    }
    
    /**
     * 从最好的点集合中根据IQ值找到一个最合适的点，IQ越高找到分值越高的概率就越大
     * @param cBoard
     * @param IQ
     * IQ越高找到分值越高的概率就越大
     * @param calculate
     * 是否需要重新计算最好的点集合。 false前请保证List里面是最新的数据
     * @return
     */
    public BestPoint getBestPointByIQ(ChessBoard cBoard, int IQ, boolean calculate){
	List<BestPoint> findBetterByScore = new ArrayList<BestPoint>();
	if(calculate){
	    findBetterByScore = findBetterByScore(cBoard);
	}else{
	    findBetterByScore = cBoard.getForecastPointMsg().getBetterPoints();
	}
	BestPoint bestPoint = RandomUtils.choiceElement(findBetterByScore, GobangConstant.COMPUTER_MAX_IQ, IQ);
	return bestPoint;
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
	return jsonString;
    }

    public static void main(String[] args) {
	logger.error("哈哈");
	ChessBoard cbBoard = new ChessBoard();
	BestPoint bPoint = new BestPoint();
	GobangLogic.getMe().dropChess(cbBoard, new ChessPoint(6, 7), ChessPlayer.BLACK);
	List<BestPoint> findBetterByScore = GobangLogic.getMe().findBetterByScore(cbBoard);
	for(BestPoint bp : findBetterByScore){
	    System.out.println(bp);
	}
	System.out.println("--------");
	int num = 1;
	while (!GameState.OVER.compare(cbBoard.getGameState())) {
	    GobangLogic.getMe().dropChess(cbBoard, GobangLogic.getMe().getBestPointByIQ(cbBoard, 100).getPoint(), ChessPlayer.WHITE);
	    for (BestPoint bp : findBetterByScore) {
		System.out.println(bp);
	    }
	    System.out.println("--------");
	    GobangLogic.getMe().dropChess(cbBoard, GobangLogic.getMe().getBestPointByIQ(cbBoard, 100).getPoint(), ChessPlayer.BLACK);
	    for (BestPoint bp : findBetterByScore) {
		System.out.println(bp);
	    }
	    System.out.println("--------");
	    num +=2;
	}
	System.out.println(num);
//	BestPoint bestPointByIQ = GobangLogic.getMe().getBestPointByIQ(cbBoard, 100);
//	for(BestPoint bp : findBetterByScore){
//	    System.out.println(bp);
//	}
//	System.out.println("--------");
//	String listToJson = GobangLogic.getMe().listToJson(cbBoard);
////	System.err.println(listToJson);
//	GobangLogic.getMe().dropChess(cbBoard, bestPointByIQ.getPoint(), ChessPlayer.WHITE);
//	findBetterByScore = GobangLogic.getMe().findBetterByScore(cbBoard);
//	for(BestPoint bp : findBetterByScore){
//	    System.out.println(bp);
//	}
//	System.out.println("--------");
//	GobangLogic.getMe().dropChess(cbBoard, GobangLogic.getMe().getBestPointByIQ(cbBoard, 100).getPoint(), ChessPlayer.BLACK);
//	for(BestPoint bp : findBetterByScore){
//	    System.out.println(bp);
//	}
//	System.out.println("--------");
//	GobangLogic.getMe().dropChess(cbBoard, GobangLogic.getMe().getBestPointByIQ(cbBoard, 100).getPoint(), ChessPlayer.WHITE);
//	for(BestPoint bp : findBetterByScore){
//	    System.out.println(bp);
//	}
//	System.out.println("--------");
//	cbBoard.getForecastPointMsg().getBetterPoints().add(bPoint);
//	bPoint.setPoint(new ChessPoint(2, 3));
//	String jsonString = JSON.toJSONString(cbBoard.getForecastPointMsg().getBetterPoints());
//	StringBuffer sb = new StringBuffer("123456789");
//	String substring = sb.substring(0, 6);
//	System.out.println(substring);
    }
}
