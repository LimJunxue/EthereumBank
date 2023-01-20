// SPDX-License-Identifier: MIT
pragma solidity >=0.8.0 <0.9.0;
import "hardhat/console.sol";
import "@openzeppelin/contracts/utils/Strings.sol";

contract Bank {
    address account;
    mapping (address => uint) balances;
    mapping (address => string) keys;

    constructor(address _account, uint _balance) {
        account = _account;
        balances[account] = _balance;
    }

    function verify(string memory key, string memory signature) public view returns (bool) {
        // not possible to decrypt rsa yet?
        return true;
    }

    function send(uint id, uint timestamp, address from, address to, uint amount, string memory key, string memory signature) public returns (uint) {
        require(from == account);
        require(verify(key, signature));
        keys[from] = key;

        if (amount > balances[from]) {
            revert(string(abi.encodePacked("Insufficient balance, requested: ", Strings.toString(amount), ", available: ", Strings.toString(balances[from]))));
        }

        balances[from] -= amount;
        balances[to] += amount;
        return id;
    }

    function getBalance(uint id, uint timestamp, address from, string memory key, string memory signature) public view returns (uint) {
        require(from == account);
        require(verify(key, signature));

        return balances[from];
    }
}