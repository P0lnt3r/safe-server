package org.anwang.safe.server.blockchain.web3;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

public class DecodeUtils {

    public static <T extends Type> T decode( String raw , String solidityType , Class<T> clazz ) throws ClassNotFoundException {
        TypeReference typeReference = TypeReference.makeTypeReference(solidityType);
        return (T)FunctionReturnDecoder.decodeIndexedValue(raw,typeReference);
    }

    public static void main(String[] args) throws Exception{
        String raw = "0x00000000000000000000000000000000000000000000003631c3644f35c30000";
        Uint256 data = decode(raw , "uint256" , Uint256.class);
        System.out.println(data.getValue());
    }

}
