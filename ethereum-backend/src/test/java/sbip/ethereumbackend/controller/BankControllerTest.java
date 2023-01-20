package sbip.ethereumbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import sbip.ethereumbackend.Contracts_Bank_sol_Bank;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private String port = "8080";

    private static Web3j web3j;
    private static Credentials credentials;

    private static String nodeProvider;
    private static String token;
    private static String walletPassword;
    private static String walletPath = "";

    private static Contracts_Bank_sol_Bank bank;

    void setup() throws Exception {
        Properties configProps = new Properties();
        InputStream iStream = new ClassPathResource("application-test.properties").getInputStream();
        configProps.load(iStream);
        nodeProvider = (String) configProps.get("nodeProvider");
        token = (String) configProps.get("token");
        walletPassword = (String) configProps.get("walletPassword");

        web3j = Web3j.build(new HttpService(nodeProvider + token));
        try {
            credentials = WalletUtils.loadCredentials(walletPassword, walletPath);
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }

        bank = Contracts_Bank_sol_Bank.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT,
                "0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2", BigInteger.valueOf(10)).send();
    }

    @Test
    void getBalanceSuccess() throws Exception {
        if (web3j == null || credentials == null) {
            setup();
        }

//        BigInteger actual = bank.getBalance(
//                BigInteger.valueOf(1),
//                BigInteger.valueOf(2),
//                "0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2", "", "")
//                .send();
//
//        Assertions.assertEquals(BigInteger.valueOf(10), actual);

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("id", 1);
        arguments.put("timestamp", 2);
        arguments.put("from", "0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2");
        arguments.put("key", "00");
        arguments.put("signature", "00");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("id", "1");
        responseHeaders.set("balance", "10");
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(responseHeaders, HttpStatus.OK);

        assertThat(this.restTemplate.getForEntity(
                "http://localhost:" + port + "/getBalance/0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2",
                String.class,
                arguments)).isEqualTo(expectedResponse);
    }

    @Test
    void sendSuccess() throws Exception {
        if (web3j == null || credentials == null) {
            setup();
        }

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("id", 1);
        arguments.put("timestamp", 2);
        arguments.put("from", "0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2");
        arguments.put("to", "0x4B20993Bc481177ec7E8f571ceCaE8A9e22C02db");
        arguments.put("amount", "10");
        arguments.put("key", "00");
        arguments.put("signature", "00");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("id", "1");
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(responseHeaders, HttpStatus.OK);

        assertThat(this.restTemplate.getForEntity(
                "http://localhost:" + port + "/send/0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2",
                String.class,
                arguments)).isEqualTo(expectedResponse);
    }
}