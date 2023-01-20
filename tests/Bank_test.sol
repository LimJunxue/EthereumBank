// SPDX-License-Identifier: MIT

pragma solidity >=0.8.0 <0.9.0;
import "remix_tests.sol"; // this import is automatically injected by Remix.
import "remix_accounts.sol";
import "../Bank.sol";

contract BankTest {
    event Log(string message);

    Bank bankToTest;
    uint id;

    function beforeAll() public {
        bankToTest = new Bank(msg.sender, 10);
    }

    function beforeEach() public {
        id += 1;
    }

    function testInitialBalance () public {
        Assert.equal(bankToTest.getBalance(id, block.timestamp, msg.sender, "", ""), 10, "Balance is not 10");
    }

    function testSendSuccess () public {
        try bankToTest.send(id, block.timestamp, msg.sender, TestsAccounts.getAccount(1), 10, "", "") returns (uint i) {
            Assert.equal(i, id, "Send failed");
        } catch Error(string memory) {
            Assert.ok(false, "Failed with reason: ");
        }
    }

    function testSendFailure () public {
        try bankToTest.send(id, block.timestamp, msg.sender, TestsAccounts.getAccount(1), 1, "", "") returns (uint) {
            Assert.ok(false, "Method should fail");
        } catch Error(string memory reason) {
            Assert.equal(reason, "Insufficient balance, requested: 1, available: 0", "Failed with unexpected reason");
        }
    }
}