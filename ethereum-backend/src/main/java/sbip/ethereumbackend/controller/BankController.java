package sbip.ethereumbackend.controller;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import sbip.ethereumbackend.Contracts_Bank_sol_Bank;

@Controller
public class BankController {
    Logger logger = LoggerFactory.getLogger(BankController.class);

    private static Web3j web3j;
    private static Credentials credentials;

    @Value("${nodeProvider}")
    private String nodeProvider;
    @Value("${token}")
    private String token;
    @Value("${walletPassword}")
    private String walletPassword;
    @Value("${walletPath}")
    private String walletPath;

    private void connect() throws CipherException, IOException {
        web3j = Web3j.build(new HttpService(nodeProvider + token));
        credentials = WalletUtils.loadCredentials(walletPassword, walletPath);
    }

    @GetMapping("/getBalance/{contract}")
    public ResponseEntity<String> getBalance(@PathVariable String contract, @RequestBody Map<String, Object> bodyMap) throws Exception {
        if (web3j == null || credentials == null) {
            connect();
        }

        Contracts_Bank_sol_Bank bankAccount;
        try {
            bankAccount = getBankContract(contract);
        } catch (Exception e) {
            return new ResponseEntity<>("Wrong contract" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BigInteger id = BigInteger.valueOf((Integer) bodyMap.get("id"));
        BigInteger timestamp = BigInteger.valueOf((Integer) bodyMap.get("timestamp"));
        String from = (String) bodyMap.get("from");
        String key = ((String) bodyMap.get("key"));
        String signature = ((String) bodyMap.get("signature"));
        logger.info(String.valueOf(bankAccount.isValid()));

        BigInteger reply;
        try {
            reply = bankAccount.getBalance(id, timestamp, from, key, signature).send();
        } catch (Exception e) {
            return new ResponseEntity<>("Something is wrong in the request body. " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("id", id.toString());
        responseHeaders.set("balance", reply.toString());
        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/send/{contract}")
    public ResponseEntity<String> send(@PathVariable String contract, @RequestBody Map<String, Object> bodyMap) throws Exception {
        if (web3j == null || credentials == null) {
            connect();
        }

        Contracts_Bank_sol_Bank bankAccount;
        try {
            bankAccount = getBankContract(contract);
        } catch (Exception e) {
            return new ResponseEntity<>("Wrong contract" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BigInteger id = BigInteger.valueOf((Integer) bodyMap.get("id"));
        BigInteger timestamp = BigInteger.valueOf((Integer) bodyMap.get("timestamp"));
        String from = (String) bodyMap.get("from");
        String to = (String) bodyMap.get("to");
        BigInteger amount = BigInteger.valueOf((Integer) bodyMap.get("amount"));
        String key = ((String) bodyMap.get("key"));
        String signature = ((String) bodyMap.get("signature"));

        TransactionReceipt reply = bankAccount.send(id, timestamp, from, to, amount, key, signature).send();

        if (!reply.isStatusOK()) {
            return new ResponseEntity<>("Something is wrong in the request body. " + reply,
                    HttpStatus.BAD_REQUEST);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("id", id.toString());
        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }

    private Contracts_Bank_sol_Bank getBankContract(String contract) throws Exception {
        return Contracts_Bank_sol_Bank.load(contract, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }
}
