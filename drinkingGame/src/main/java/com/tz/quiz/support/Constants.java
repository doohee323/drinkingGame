package com.tz.quiz.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Constants {

    // conditions
    final public static String[] winningNumberSet = {"1,6","2,5","3,4","4,3","5,2","6,1","5,6","6,5","1,1","2,2","3,3","4,4","5,5","6,6"
    };
    final public static int defaultRollSpeed = 2;
    final public static int defaultRollMaxCnt = 5;
    final public static int maxDrinkingTurn = 100;
    
    public Constants () {
    }
    
    public static boolean isWin(String input) {
        List<String> winSet = new ArrayList<String>();
        winSet.addAll(Arrays.asList(winningNumberSet));
    	return winSet.contains(input);
    }

}
