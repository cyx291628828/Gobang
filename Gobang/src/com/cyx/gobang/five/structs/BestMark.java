package com.cyx.gobang.five.structs;

import java.util.ArrayList;
import java.util.List;

import com.cyx.gobang.five.enums.ChessPlayer;

/**
 * ���ڵ�ǰ�����˵��õļ�����
 * @author ������
 *
 */
public class BestMark{
    /**
     * ��ǰ�����˵��õĵ㣬Ĭ�Ϻ��塣
     */
    private ChessPlayer player = ChessPlayer.BLACK;
    /**
     * ����ǰʮ�����Ϻõ����ӵ�
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();//��õ�ʮ����λ��
    

}
