package org.anwang.safe.server.blockchain.web3;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class TransactionContractSend {

    public static String send(Web3j admin,Credentials credentials, String to , String fnName , String[] inputTypes , Object[] inputParams , BigInteger value , BigInteger nonce , BigInteger gasPrice , BigInteger gasLimit) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        Function function = FunctionEncoder.makeFunction(fnName , Arrays.asList(inputTypes) , Arrays.asList(inputParams) , new ArrayList<>());
        String raw = FunctionEncoder.encode(function);
        return send(admin,credentials,to,raw,value,nonce,gasPrice,gasLimit);

    }

    public static String send(Web3j admin , Credentials credentials , String to , String raw , BigInteger value , BigInteger nonce , BigInteger gasPrice , BigInteger gasLimit ) throws IOException {
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                to,
                value,
                raw
        );
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction,credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        return admin.ethSendRawTransaction(hexValue).send().getTransactionHash();
    }

    public static String send(Web3j admin , long chainId , Credentials credentials , String to , String raw , BigInteger value , BigInteger nonce , BigInteger gasLimit,
                              BigInteger maxPriorityFeePerGas,
                              BigInteger maxFeePerGas ) throws IOException {
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                chainId,
                nonce,
                gasLimit,
                to,
                value,
                raw,
                maxPriorityFeePerGas,
                maxFeePerGas
        );
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction,credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        return admin.ethSendRawTransaction(hexValue).send().getTransactionHash();
    }


}
