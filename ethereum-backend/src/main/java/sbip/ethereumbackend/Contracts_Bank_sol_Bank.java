package sbip.ethereumbackend;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Contracts_Bank_sol_Bank extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161092b38038061092b83398101604081905261002f91610061565b600080546001600160a01b039093166001600160a01b031990931683178155918252600160205260409091205561009b565b6000806040838503121561007457600080fd5b82516001600160a01b038116811461008b57600080fd5b6020939093015192949293505050565b610881806100aa6000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806311091707146100465780631dad4d5f1461006c5780639deb17f51461007f575b600080fd5b610059610054366004610466565b6100a2565b6040519081526020015b60405180910390f35b61005961007a366004610508565b6101e4565b61009261008d366004610590565b610233565b6040519015158152602001610063565b600080546001600160a01b038781169116146100bd57600080fd5b6100c78383610233565b6100d057600080fd5b6001600160a01b03861660009081526002602052604090206100f2848261067d565b506001600160a01b03861660009081526001602052604090205484111561017e5761011c8461023c565b6001600160a01b03871660009081526001602052604090205461013e9061023c565b60405160200161014f929190610761565b60408051601f198184030181529082905262461bcd60e51b8252610175916004016107dc565b60405180910390fd5b6001600160a01b038616600090815260016020526040812080548692906101a6908490610825565b90915550506001600160a01b038516600090815260016020526040812080548692906101d3908490610838565b909155509798975050505050505050565b600080546001600160a01b038581169116146101ff57600080fd5b6102098383610233565b61021257600080fd5b5050506001600160a01b031660009081526001602052604090205492915050565b60015b92915050565b60606000610249836102cf565b600101905060008167ffffffffffffffff811115610269576102696103c3565b6040519080825280601f01601f191660200182016040528015610293576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a850494508461029d57509392505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b831061030e5772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef8100000000831061033a576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc10000831061035857662386f26fc10000830492506010015b6305f5e1008310610370576305f5e100830492506008015b612710831061038457612710830492506004015b60648310610396576064830492506002015b600a83106102365760010192915050565b80356001600160a01b03811681146103be57600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126103ea57600080fd5b813567ffffffffffffffff80821115610405576104056103c3565b604051601f8301601f19908116603f0116810190828211818310171561042d5761042d6103c3565b8160405283815286602085880101111561044657600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600080600060e0888a03121561048157600080fd5b8735965060208801359550610498604089016103a7565b94506104a6606089016103a7565b93506080880135925060a088013567ffffffffffffffff808211156104ca57600080fd5b6104d68b838c016103d9565b935060c08a01359150808211156104ec57600080fd5b506104f98a828b016103d9565b91505092959891949750929550565b600080600080600060a0868803121561052057600080fd5b8535945060208601359350610537604087016103a7565b9250606086013567ffffffffffffffff8082111561055457600080fd5b61056089838a016103d9565b9350608088013591508082111561057657600080fd5b50610583888289016103d9565b9150509295509295909350565b600080604083850312156105a357600080fd5b823567ffffffffffffffff808211156105bb57600080fd5b6105c7868387016103d9565b935060208501359150808211156105dd57600080fd5b506105ea858286016103d9565b9150509250929050565b600181811c9082168061060857607f821691505b60208210810361062857634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561067857600081815260208120601f850160051c810160208610156106555750805b601f850160051c820191505b8181101561067457828155600101610661565b5050505b505050565b815167ffffffffffffffff811115610697576106976103c3565b6106ab816106a584546105f4565b8461062e565b602080601f8311600181146106e057600084156106c85750858301515b600019600386901b1c1916600185901b178555610674565b600085815260208120601f198616915b8281101561070f578886015182559484019460019091019084016106f0565b508582101561072d5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60005b83811015610758578181015183820152602001610740565b50506000910152565b7f496e73756666696369656e742062616c616e63652c207265717565737465643a8152600160fd1b6020820152600083516107a381602185016020880161073d565b6c0161030bb30b4b630b136329d1609d1b60219184019182015283516107d081602e84016020880161073d565b01602e01949350505050565b60208152600082518060208401526107fb81604085016020870161073d565b601f01601f19169190910160400192915050565b634e487b7160e01b600052601160045260246000fd5b818103818111156102365761023661080f565b808201808211156102365761023661080f56fea2646970667358221220d2143fe3828d6807407de3c24475ff40d79c37d84f9cf96c13b60c1c3fd502aa64736f6c63430008110033";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_SEND = "send";

    public static final String FUNC_VERIFY = "verify";

    @Deprecated
    protected Contracts_Bank_sol_Bank(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_Bank_sol_Bank(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_Bank_sol_Bank(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_Bank_sol_Bank(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getBalance(BigInteger id, BigInteger timestamp, String from, String key, String signature) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Utf8String(key), 
                new org.web3j.abi.datatypes.Utf8String(signature)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> send(BigInteger id, BigInteger timestamp, String from, String to, BigInteger amount, String key, String signature) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.Utf8String(key), 
                new org.web3j.abi.datatypes.Utf8String(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> verify(String key, String signature) {
        final Function function = new Function(FUNC_VERIFY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(key), 
                new org.web3j.abi.datatypes.Utf8String(signature)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Contracts_Bank_sol_Bank load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Bank_sol_Bank(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_Bank_sol_Bank load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Bank_sol_Bank(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_Bank_sol_Bank load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_Bank_sol_Bank(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_Bank_sol_Bank load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_Bank_sol_Bank(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_Bank_sol_Bank> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _account, BigInteger _balance) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.generated.Uint256(_balance)));
        return deployRemoteCall(Contracts_Bank_sol_Bank.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Contracts_Bank_sol_Bank> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _account, BigInteger _balance) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.generated.Uint256(_balance)));
        return deployRemoteCall(Contracts_Bank_sol_Bank.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contracts_Bank_sol_Bank> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _account, BigInteger _balance) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.generated.Uint256(_balance)));
        return deployRemoteCall(Contracts_Bank_sol_Bank.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contracts_Bank_sol_Bank> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _account, BigInteger _balance) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.generated.Uint256(_balance)));
        return deployRemoteCall(Contracts_Bank_sol_Bank.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
