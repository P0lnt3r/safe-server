package org.anwang.safe.server.blockchain.web3;

import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

public class EventLogTopic0 {

    /**
     * generate("Transfer(address,address,uint256)");
     */
    public static final String Transfer = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";

    public static String generate(String methodSignature) {
        final byte[] input = methodSignature.getBytes();
        final byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash);
    }

    public static void main(String[] args) {
        System.out.println(Transfer);
    }

}
