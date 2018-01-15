package src.com.cyx.gobang.five.chessboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import src.com.cyx.gobang.five.constant.GobangConstant;
import src.com.cyx.gobang.five.enums.ChessPlayer;
import src.com.cyx.gobang.five.enums.GameState;
import src.com.cyx.gobang.five.interfaces.IGame;
import src.com.cyx.gobang.five.structs.ChessPointMsg;
import src.com.cyx.gobang.five.structs.ForecastPointMsg;

/**
 * 
 * @author 陈宇轩
 * @date 2017年11月15
 * @function 棋盘上的逻辑主要功能
 *
 */
public class ChessBoard extends IGame {
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
     * 棋盘的所有坐标点信息和目前最好的十二个点
     */
    private ForecastPointMsg forecastPointMsg = new ForecastPointMsg();
    /**
     * 游戏状态 默认为游戏开始前
     */
    private AtomicInteger gameState = new AtomicInteger(GameState.START_BEFORE.getState());
    /**
     * 保存还没下且电脑已经预测分数的 150个点 队列里面只保存key，分数存在forecastMap中
     */
    private Queue<String> forecastPoint = new LinkedList<String>();
    /**
     * 保存还没下且电脑已经预测分数的 150个点
     */
    private Map<String, ForecastPointMsg> forecastMap = new HashMap<String, ForecastPointMsg>();
    /**
     * 保存已经下子的记录
     */
    private List<ChessPointMsg> recordList = new ArrayList<ChessPointMsg>();

    // 构造方法
    /**
     * 初始化棋盘 棋盘大小15*15
     */
    public ChessBoard() {
	init();
    }

    public ChessBoard(int chess_size) {
	CHESS_SIZE = chess_size;
	CHESS_GRID = chess_size - 1;
	init();
    }

    // get 和 set 方法
    private int getGameState() {
	return gameState.get();
    }

    /**
     * 请使用upState(GameState) 方法
     * 
     * @param gameState
     */
    @Deprecated
    private void setGameState(int gameState) {
	this.gameState.set(gameState);
    }

    public ChessPlayer getPlayer() {
	return player;
    }

    public void setPlayer(ChessPlayer player) {
	this.player = player;
    }

    public ForecastPointMsg getForecastPointMsg() {
	return forecastPointMsg;
    }

    public void setForecastPointMsg(ForecastPointMsg forecastPointMsg) {
	this.forecastPointMsg = forecastPointMsg;
    }

    public Queue<String> getForecastPoint() {
	return forecastPoint;
    }

    public void setForecastPoint(Queue<String> forecastPoint) {
	this.forecastPoint = forecastPoint;
    }

    public Map<String, ForecastPointMsg> getForecastMap() {
	return forecastMap;
    }

    public void setForecastMap(Map<String, ForecastPointMsg> forecastMap) {
	this.forecastMap = forecastMap;
    }

    public List<ChessPointMsg> getRecordList() {
	return recordList;
    }

    public void setRecordList(List<ChessPointMsg> recordList) {
	this.recordList = recordList;
    }

    // -------分界线-------
    public void upState(GameState state) {
	int old_value = this.getGameState();

	int new_value = GameState.clearGroupAndSet(old_value, state);
	this.setGameState(new_value);
    }

    /**
     * 初始化棋盘信息 默认棋盘大小为15*15
     */
    private void init() {
	forecastPointMsg.init(CHESS_SIZE, 12);
	recordList.clear();
	forecastMap.clear();
	forecastPoint.clear();
	setGameState(0);
    }

    public static void main(String[] args) {
	ChessBoard chessBoard = new ChessBoard();
	chessBoard.init();
    }
}
