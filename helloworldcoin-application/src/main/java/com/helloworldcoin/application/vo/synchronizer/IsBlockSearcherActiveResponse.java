package com.helloworldcoin.application.vo.synchronizer;

/**
 *
 * @author x.king xdotking@gmail.com
 */
public class IsBlockSearcherActiveResponse {

    private boolean blockSearcherInActiveState;




    //region get set
    public boolean isBlockSearcherInActiveState() {
        return blockSearcherInActiveState;
    }

    public void setBlockSearcherInActiveState(boolean blockSearcherInActiveState) {
        this.blockSearcherInActiveState = blockSearcherInActiveState;
    }
    //endregion
}
