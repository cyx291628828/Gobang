package com.cyx.gobang.five.interfaces;

import com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGameOver {
    
    /**
     * 在游戏结束的时调用
     * 游戏出现胜负之后调用
     * 玩家胜利
     * @param cBoard
     */
    public void onGameWin(ChessBoard cBoard);
    
    /**
     * 在游戏结束的时调用
     * 游戏出现胜负之后调用
     * 玩家失败
     * @param cBoard
     */
    public void onGameLose(ChessBoard cBoard);
}
