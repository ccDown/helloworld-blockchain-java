package com.helloworldcoin.application.vo;

/**
 *
 * @author x.king xdotking@gmail.com
 */
public class NodeConsoleApplicationApi {

    public static final String IS_MINER_ACTIVE = "/Api/NodeConsoleApplication/IsMinerActive";
    public static final String ACTIVE_MINER = "/Api/NodeConsoleApplication/ActiveMiner";
    public static final String DEACTIVE_MINER = "/Api/NodeConsoleApplication/DeactiveMiner";



    public static final String IS_BLOCK_SEARCHER_ACTIVE = "/Api/NodeConsoleApplication/IsBlockSearcherActive";
    public static final String ACTIVE_BLOCK_SEARCHER = "/Api/NodeConsoleApplicationle/ActiveBlockSearcher";
    public static final String DEACTIVE_BLOCK_SEARCHER = "/Api/NodeConsoleApplication/DeactiveBlockSearcher";



    public static final String IS_NODE_SEARCHER_ACTIVE = "/Api/NodeConsoleApplication/IsNodeSearcherActive";
    public static final String ACTIVE_NODE_SEARCHER = "/Api/NodeConsoleApplication/ActiveNodeSearcher";
    public static final String DEACTIVE_NODE_SEARCHER = "/Api/NodeConsoleApplication/DeactiveNodeSearcher";

    public static final String SET_MINER_MINE_MAX_BLOCK_HEIGHT = "/Api/NodeConsoleApplication/SetMinerMineMaxBlockHeight";
    public static final String GET_MINER_MINE_MAX_BLOCK_HEIGHT = "/Api/NodeConsoleApplication/GetMinerMineMaxBlockHeight";

    public static final String ADD_NODE = "/Api/NodeConsoleApplication/AddNode";
    public static final String UPDATE_NODE = "/Api/NodeConsoleApplication/UpdateNode";
    public static final String DELETE_NODE = "/Api/NodeConsoleApplication/DeleteNode";
    public static final String QUERY_ALL_NODES = "/Api/NodeConsoleApplication/QueryAllNodes";



    public static final String DELETE_BLOCKS = "/Api/NodeConsoleApplication/DeleteBlocks";
}
