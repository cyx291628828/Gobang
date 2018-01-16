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
 * @author ������
 * @date 2017��11��15
 * @function �����ϵ��߼���Ҫ����
 *
 */
public class ChessBoard extends IGame {
    /**
     * Ĭ�����̴�СΪGobangConstant.CHESS_SIZE = 15
     */
    private int CHESS_SIZE = GobangConstant.CHESS_SIZE;
    /**
     * Ĭ�����̸�����ΪGobangConstant.CHESS_GRID = 14
     */
    private int CHESS_GRID = GobangConstant.CHESS_GRID;
    /**
     * ��ǰ������ң�Ĭ�Ϻ�������
     */
    private ChessPlayer player = ChessPlayer.BLACK;
    /**
     * ���̵������������Ϣ��Ŀǰ��õ�ʮ������
     */
    private ForecastPointMsg forecastPointMsg = new ForecastPointMsg();
    /**
     * ��Ϸ״̬ Ĭ��Ϊ��Ϸ��ʼǰ
     */
    private AtomicInteger gameState = new AtomicInteger(GameState.START_BEFORE.getState());
    /**
     * ���滹û���ҵ����Ѿ�Ԥ������� 150���� ��������ֻ����key����������forecastMap��
     */
    private Queue<String> forecastPoint = new LinkedList<String>();
    /**
     * ���滹û���ҵ����Ѿ�Ԥ������� 150����
     */
    private Map<String, ForecastPointMsg> forecastMap = new HashMap<String, ForecastPointMsg>();
    /**
     * �����Ѿ����ӵļ�¼
     */
    private List<ChessPointMsg> recordList = new ArrayList<ChessPointMsg>();

    // ���췽��
    /**
     * ��ʼ������ ���̴�С15*15
     */
    public ChessBoard() {
	init();
    }

    public ChessBoard(int chess_size) {
	CHESS_SIZE = chess_size;
	CHESS_GRID = chess_size - 1;
	init();
    }

    // get �� set ����
    private int getGameState() {
	return gameState.get();
    }

    /**
     * ��ʹ��upState(GameState) ����
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

    // -------�ֽ���-------
    public void upState(GameState state) {
	int old_value = this.getGameState();

	int new_value = GameState.clearGroupAndSet(old_value, state);
	this.setGameState(new_value);
    }

    /**
     * ��ʼ��������Ϣ Ĭ�����̴�СΪ15*15
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
