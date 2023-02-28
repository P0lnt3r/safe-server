package org.anwang.safe.server.blockchain.web3.uniswap;

import org.anwang.safe.server.blockchain.web3.Constants;
import org.anwang.safe.server.blockchain.web3.MethodID;
import org.anwang.safe.server.blockchain.web3.ReadonlyContractCall;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GetReserves {

    public static BigInteger[] getReserves(Web3j web3j , String pairAddress) throws Exception {
        String encode = FunctionEncoder.encode(MethodID.generate("getReserves()") , new ArrayList<>());
        String raw = ReadonlyContractCall.call(web3j , Constants.NULL_ADDRESS, pairAddress , encode);
        List outputTypes = new ArrayList<TypeReference<Type>>();
        outputTypes.add(new TypeReference<Uint256>(){});
        outputTypes.add(new TypeReference<Uint256>(){});
        outputTypes.add(new TypeReference<Uint256>(){});
        List<Type> result = FunctionReturnDecoder.decode(raw , outputTypes);
        return new BigInteger[]{
                ((Uint256)result.get(0) ).getValue(),
                ((Uint256)result.get(1) ).getValue()
        };
    }

}
