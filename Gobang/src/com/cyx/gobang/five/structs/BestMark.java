package com.cyx.gobang.five.structs;

import java.util.ArrayList;
import java.util.List;

import com.cyx.gobang.five.enums.ChessPlayer;

/**
 * 对于当前玩家来说最好的几个点
 * @author 陈宇轩
 *
 */
public class BestMark{
    /**
     * 当前玩家来说最好的点，默认黑棋。
     */
    private ChessPlayer player = ChessPlayer.BLACK;
    /**
     * 保存前十二个较好的落子点
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();//最好的十二个位置
    

}
