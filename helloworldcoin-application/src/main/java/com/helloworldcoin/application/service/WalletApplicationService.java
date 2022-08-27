package com.helloworldcoin.application.service;

import com.helloworldcoin.application.vo.transaction.SubmitTransactionToBlockchainNetworkRequest;
import com.helloworldcoin.application.vo.transaction.SubmitTransactionToBlockchainNetworkResponse;
import com.helloworldcoin.application.vo.wallet.BuildTransactionRequest;
import com.helloworldcoin.application.vo.wallet.BuildTransactionResponse;

/**
 *
 * @author x.king xdotking@gmail.com
 */
public interface WalletApplicationService {


    SubmitTransactionToBlockchainNetworkResponse submitTransactionToBlockchainNetwork(SubmitTransactionToBlockchainNetworkRequest request);

    BuildTransactionResponse buildTransaction(BuildTransactionRequest request);
}
