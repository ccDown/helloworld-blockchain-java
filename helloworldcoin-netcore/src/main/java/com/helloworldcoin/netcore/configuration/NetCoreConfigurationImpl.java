package com.helloworldcoin.netcore.configuration;

import com.helloworldcoin.util.ByteUtil;
import com.helloworldcoin.util.FileUtil;
import com.helloworldcoin.util.KvDbUtil;

/**
 *
 * @author x.king xdotking@gmail.com
 */
public class NetCoreConfigurationImpl implements NetCoreConfiguration {

    private String netCorePath;
    private static final String NETCORE_CONFIGURATION_DATABASE_NAME = "NetCoreConfigurationDatabase";

    private static final String IS_BLOCK_SEARCHER_ACTIVE_OPTION_KEY = "IS_BLOCK_SEARCHER_ACTIVE";
    private static final boolean IS_BLOCK_SEARCHER_ACTIVE_OPTION_DEFAULT_VALUE = true;

    private static final String IS_NODE_SEARCHER_ACTIVE_OPTION_KEY = "IS_NODE_SEARCHER_ACTIVE";
    private static final boolean IS_NODE_SEARCHER_ACTIVE_OPTION_DEFAULT_VALUE = true;

    private static final long SEARCH_NODE_TIME_INTERVAL = 1000 * 60 * 2;
    private static final long SEARCH_BLOCKCHAIN_HEIGHT_TIME_INTERVAL = 1000 * 60 * 2;
    private static final long SEARCH_BLOCKS_TIME_INTERVAL = 1000 * 60 * 2;
    private static final long BLOCKCHAIN_HEIGHT_BROADCASTER_TIME_INTERVAL = 1000 * 20;
    private static final long BLOCK_BROADCASTER_TIME_INTERVAL = 1000 * 20;
    private static final long ADD_SEED_NODE_TIME_INTERVAL = 1000 * 60 * 2;
    private static final long NODE_BROADCAST_TIME_INTERVAL = 1000 * 60 * 2;
    private static final long NODE_CLEAN_TIME_INTERVAL = 1000 * 60 * 10;


    private static final long HARD_FORK_BLOCK_COUNT = 100;

    private static final long SEARCH_UNCONFIRMED_TRANSACTIONS_TIME_INTERVAL = 1000 * 60 * 2;

    public NetCoreConfigurationImpl(String netCorePath) {
        FileUtil.makeDirectory(netCorePath);
        this.netCorePath = netCorePath;
    }


    @Override
    public String getNetCorePath() {
        return netCorePath;
    }

    @Override
    public boolean isBlockSearcherActive() {
        byte[] bytesConfigurationValue = getConfigurationValue(ByteUtil.stringToUtf8Bytes(IS_BLOCK_SEARCHER_ACTIVE_OPTION_KEY));
        if(bytesConfigurationValue == null){
            return IS_BLOCK_SEARCHER_ACTIVE_OPTION_DEFAULT_VALUE;
        }
        return ByteUtil.utf8BytesToBoolean(bytesConfigurationValue);
    }

    @Override
    public void activeBlockSearcher() {
        addOrUpdateConfiguration(ByteUtil.stringToUtf8Bytes(IS_BLOCK_SEARCHER_ACTIVE_OPTION_KEY),ByteUtil.booleanToUtf8Bytes(true));
    }

    @Override
    public void deactiveBlockSearcher() {
        addOrUpdateConfiguration(ByteUtil.stringToUtf8Bytes(IS_BLOCK_SEARCHER_ACTIVE_OPTION_KEY),ByteUtil.booleanToUtf8Bytes(false));
    }

    @Override
    public boolean isNodeSearcherActive() {
        byte[] bytesConfigurationValue = getConfigurationValue(ByteUtil.stringToUtf8Bytes(IS_NODE_SEARCHER_ACTIVE_OPTION_KEY));
        if(bytesConfigurationValue == null){
            return IS_NODE_SEARCHER_ACTIVE_OPTION_DEFAULT_VALUE;
        }
        return ByteUtil.utf8BytesToBoolean(bytesConfigurationValue);
    }

    @Override
    public void activeNodeSearcher() {
        addOrUpdateConfiguration(ByteUtil.stringToUtf8Bytes(IS_NODE_SEARCHER_ACTIVE_OPTION_KEY),ByteUtil.booleanToUtf8Bytes(true));
    }

    @Override
    public void deactiveNodeSearcher() {
        addOrUpdateConfiguration(ByteUtil.stringToUtf8Bytes(IS_NODE_SEARCHER_ACTIVE_OPTION_KEY),ByteUtil.booleanToUtf8Bytes(false));
    }

    @Override
    public long getNodeSearchTimeInterval() {
        return SEARCH_NODE_TIME_INTERVAL;
    }

    @Override
    public long getBlockchainHeightSearchTimeInterval() {
        return SEARCH_BLOCKCHAIN_HEIGHT_TIME_INTERVAL;
    }

    @Override
    public long getBlockSearchTimeInterval() {
        return SEARCH_BLOCKS_TIME_INTERVAL;
    }

    @Override
    public long getBlockchainHeightBroadcastTimeInterval() {
        return BLOCKCHAIN_HEIGHT_BROADCASTER_TIME_INTERVAL;
    }

    @Override
    public long getBlockBroadcastTimeInterval() {
        return BLOCK_BROADCASTER_TIME_INTERVAL;
    }

    @Override
    public long getSeedNodeInitializeTimeInterval() {
        return ADD_SEED_NODE_TIME_INTERVAL;
    }

    @Override
    public long getNodeBroadcastTimeInterval() {
        return NODE_BROADCAST_TIME_INTERVAL;
    }

    @Override
    public long getHardForkBlockCount() {
        return HARD_FORK_BLOCK_COUNT;
    }

    @Override
    public long getUnconfirmedTransactionsSearchTimeInterval() {
        return SEARCH_UNCONFIRMED_TRANSACTIONS_TIME_INTERVAL;
    }

    @Override
    public long getNodeCleanTimeInterval() {
        return NODE_CLEAN_TIME_INTERVAL;
    }


    private byte[] getConfigurationValue(byte[] configurationKey) {
        byte[] bytesConfigurationValue = KvDbUtil.get(getNetCoreConfigurationDatabasePath(), configurationKey);
        return bytesConfigurationValue;
    }

    private void addOrUpdateConfiguration(byte[] configurationKey, byte[] configurationValue) {
        KvDbUtil.put(getNetCoreConfigurationDatabasePath(), configurationKey, configurationValue);
    }

    private String getNetCoreConfigurationDatabasePath(){
        return FileUtil.newPath(netCorePath, NETCORE_CONFIGURATION_DATABASE_NAME);
    }
}