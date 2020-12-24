/*
 * Copyright (c) 6/17/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.util;

import java.util.Random;

//Put quick reference variables in here, reference functions should go in FunctionRef.java
public class QuickRef {

    public static final String ID="hl2", NAME="The Orange Blocks", VERSION="0.0.5"; // basic declarations

    public static final String CLIENT="agentij.orangeblocks.hl2.proxy.ClientProxy", SERVER="agentij.orangeblocks.hl2.proxy.CommonProxy"; //proxy locations for forge


    public static final float CREEPEREXPLOSIONMAXSTRENGTH = 3.9f, TNTMAXSTRENGTH =5.2f, BLOCKCREEPERESISTANCE=CREEPEREXPLOSIONMAXSTRENGTH*5/3/0.3f-0.3f,
            BLOCKTNTRESISTANCE=TNTMAXSTRENGTH*5/3/0.3f-0.3f;


    static final public Random randomGenerator = new Random();



    public static final double PLAYER_SPRINT_SPEED=0.43;
    public static final double PLAYER_WALK_SPEED=0.32;


    public static final double SMALL_ZOMBIE_SPEED = 0.29;
    public static final double ZOMBIE_MOVEMENT_SPEED=0.23;
    public static final double GHAST_DETECTION_RANGE = 100;
}
