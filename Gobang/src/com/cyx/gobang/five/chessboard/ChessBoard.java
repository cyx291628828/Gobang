package com.cyx.gobang.five.chessboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.cyx.gobang.five.constant.GobangConstant;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.enums.GameState;
import com.cyx.gobang.five.structs.BestPoint;
import com.cyx.gobang.five.structs.ChessPoint;
import com.cyx.gobang.five.structs.ChessPointMsg;
import com.cyx.gobang.five.structs.ForecastPointMsg;

/**
 * 
 * @author 陈宇轩
 * @date 2017年11月15
 * @function 棋盘上的逻辑主要功能
 *
 */
public class ChessBoard {
    /**
     * 默认棋盘大小为GobangConstant.CHESS_SIZE = 15
     */
    private int CHESS_SIZE = GobangConstant.CHESS_SIZE;
    /**
     * 默认棋盘格子数为GobangConstant.CHESS_GRID = 14
     */
    private int CHESS_GRID = GobangConstant.CHESS_GRID;
    /**
     * 当前下子玩家，默认黑棋先手
     */
    private ChessPlayer player = ChessPlayer.BLACK;
    /**
     * 棋盘的所有坐标点信息
     */
    private ChessPointMsg[][] chessPointMsg = new ChessPointMsg[CHESS_SIZE][CHESS_SIZE];
    /**
     * 游戏状态  默认为游戏开始前
     */
    private AtomicInteger gameState = new AtomicInteger(GameState.START_BEGIN.getState());
    /**
     * 保存前十二个较好的落子点
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();//最好的十二个位置
    /**
     * 保存还没下且电脑已经预测分数的 150个点  队列里面只保存key，分数存在forecastMap中
     */
    private Queue<String> forecastPoint = new LinkedList<String>();
    /**
     * 保存还没下且电脑已经预测分数的 150个点
     */
    private Map<String, ForecastPointMsg> forecastMap = new HashMap<String, ForecastPointMsg>();
    /**
     * 保存已经下子的记录
     */
    private List<ChessPointMsg> recordMap = new ArrayList<ChessPointMsg>();
    
    //get 和 set 方法
    
    public int getGameState() {
        return gameState.get();
    }
    public void setGameState(int gameState) {
        this.gameState.set(gameState);
    }
    //-------分界线-------
    
    public void doState(GameState state) {
	int old_value = this.getGameState();

	int new_value = GameState.clearGroupAndSet(old_value, state);
	this.setGameState(new_value);
    }
    
    
    public ChessBoard() { 
    }
    /**
     * 初始化棋盘信息
     */
    private void init() {
	for(int i = 0 ; i < CHESS_SIZE ; i++){
	    for(int j = 0 ; j < CHESS_SIZE ; j++){
		chessPointMsg[i][j] = new ChessPointMsg(new ChessPoint(i,j), ChessPlayer.BLANK);
	    }
	}
	recordMap.clear();
	forecastMap.clear();
	forecastPoint.clear();
	betterPoints.clear();
    }
    public static void main(String[] args) {
	ChessBoard chessBoard = new ChessBoard();
	chessBoard.init();
	ChessPointMsg chessPointMsg2 = chessBoard.chessPointMsg[5][5];
	ChessPoint point = chessPointMsg2.getPoint();
	System.out.println(point.getPoint_x());
    }
}
