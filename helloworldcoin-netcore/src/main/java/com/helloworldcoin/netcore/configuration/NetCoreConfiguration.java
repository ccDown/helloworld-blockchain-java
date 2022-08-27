package com.helloworldcoin.netcore.configuration;


/**
 * NetCore Configuration
 *
 * @author x.king xdotking@gmail.com
 */
public interface NetCoreConfiguration {


    String getNetCorePath();


    long getSeedNodeInitializeTimeInterval();
    long getNodeSearchTimeInterval();
    long getNodeBroadcastTimeInterval();
    long getNodeCleanTimeInterval();


    boolean isBlockSearcherActive();
    void activeBlockSearcher();
    void deactiveBlockSearcher() ;


    boolean isNodeSearcherActive();
    void activeNodeSearcher();
    void deactiveNodeSearcher();


    long getBlockSearchTimeInterval();
    long getBlockBroadcastTimeInterval();


    long getBlockchainHeightSearchTimeInterval();
    long getBlockchainHeightBroadcastTimeInterval();


    long getHardForkBlockCount();
    long getUnconfirmedTransactionsSearchTimeInterval();
}
