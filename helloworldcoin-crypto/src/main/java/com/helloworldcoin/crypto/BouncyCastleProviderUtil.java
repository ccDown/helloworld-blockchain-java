package com.helloworldcoin.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

/**
 * JavaCryptographyExtension Provider Util
 *
 * @author x.king xdotking@gmail.com
 */
public class BouncyCastleProviderUtil {

    public static synchronized void addBouncyCastleProvider(){
        Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if(provider == null){
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        }
    }

    public static String getBouncyCastleProviderName(){
        return BouncyCastleProvider.PROVIDER_NAME;
    }
}
