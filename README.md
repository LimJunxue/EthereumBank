# EthereumBank
Java backend and corresponding Solidity smart contract

To compile contracts in the `contracts` folder:
1. solcjs --bin --abi --optimize -o build --include-path node_modules/ --base-path . contracts/Contract.sol
(if you use solcjs)
2. web3j generate solidity -b "absolute path to contract.bin" -a "absolute path to contract.abi" -o /src/main/java -p package.name

You can use remix to test/compile, deploy.

Compiling java backend:
1. fill in .env
2. run


