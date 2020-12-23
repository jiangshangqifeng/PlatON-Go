// Copyright 2018-2020 The PlatON Network Authors
// This file is part of the PlatON-Go library.
//
// The PlatON-Go library is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// The PlatON-Go library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with the PlatON-Go library. If not, see <http://www.gnu.org/licenses/>.

package cbft

import (
	"crypto/ecdsa"
	"github.com/PlatONnetwork/PlatON-Go/p2p/enode"
	"math/big"
	"time"

	"github.com/PlatONnetwork/PlatON-Go/ethdb"

	"github.com/PlatONnetwork/PlatON-Go/core/rawdb"

	"github.com/PlatONnetwork/PlatON-Go/x/xcom"

	cvm "github.com/PlatONnetwork/PlatON-Go/common/vm"

	"github.com/PlatONnetwork/PlatON-Go/consensus/cbft/network"

	"github.com/PlatONnetwork/PlatON-Go/common"
	"github.com/PlatONnetwork/PlatON-Go/common/hexutil"
	"github.com/PlatONnetwork/PlatON-Go/consensus"
	ctypes "github.com/PlatONnetwork/PlatON-Go/consensus/cbft/types"
	"github.com/PlatONnetwork/PlatON-Go/consensus/cbft/validator"
	"github.com/PlatONnetwork/PlatON-Go/core"
	"github.com/PlatONnetwork/PlatON-Go/core/types"
	"github.com/PlatONnetwork/PlatON-Go/core/vm"
	"github.com/PlatONnetwork/PlatON-Go/crypto"
	"github.com/PlatONnetwork/PlatON-Go/crypto/bls"
	"github.com/PlatONnetwork/PlatON-Go/event"
	"github.com/PlatONnetwork/PlatON-Go/node"
	"github.com/PlatONnetwork/PlatON-Go/params"
)

var (
	testKey, _  = crypto.HexToECDSA("b71c71a67e1177ad4e901695e1b4b9ee17ae16c6668d313eac2f96dbcda3f291")
	testAddress = crypto.PubkeyToAddress(testKey.PublicKey)

	chainConfig      = params.TestnetChainConfig
	testTxPoolConfig = core.DefaultTxPoolConfig

	// twenty billion von
	//twoentyBillion, _ = new(big.Int).SetString("200000000000000000000000000000", 10)
	// two billion von
	twoBillion, _ = new(big.Int).SetString("20000000000000000000000000000", 10)
)

// NewBlock returns a new block for testing.
func NewBlock(parent common.Hash, number uint64) *types.Block {
	header := &types.Header{
		Number:      big.NewInt(int64(number)),
		ParentHash:  parent,
		Time:        uint64(time.Now().UnixNano() / 1e6),
		Extra:       make([]byte, 97),
		ReceiptHash: common.BytesToHash(hexutil.MustDecode("0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421")),
		Root:        common.BytesToHash(hexutil.MustDecode("0x0353e2f9065dd8d09ae0eb42a2e0776b07cd6915f53583d6ce0ad7b203e0be3c")),
		Coinbase:    common.Address{},
		GasLimit:    10000000000,
	}

	block := types.NewBlockWithHeader(header)
	return block
}

// NewBlock returns a new block for testing.
func NewBlockWithSign(parent common.Hash, number uint64, node *TestCBFT) *types.Block {
	header := &types.Header{
		Number:      big.NewInt(int64(number)),
		ParentHash:  parent,
		Time:        uint64(time.Now().UnixNano() / 1e6),
		Extra:       make([]byte, 97),
		ReceiptHash: common.BytesToHash(hexutil.MustDecode("0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421")),
		Root:        common.BytesToHash(hexutil.MustDecode("0x0353e2f9065dd8d09ae0eb42a2e0776b07cd6915f53583d6ce0ad7b203e0be3c")),
		Coinbase:    common.Address{},
		GasLimit:    10000000000,
	}

	sign, _ := node.engine.signFn(header.SealHash().Bytes())
	copy(header.Extra[len(header.Extra)-consensus.ExtraSeal:], sign[:])

	block := types.NewBlockWithHeader(header)
	return block
}

// GenerateKeys returns the public and private key pair for testing.
func GenerateKeys(num int) ([]*ecdsa.PrivateKey, []*bls.SecretKey) {
	pk := make([]*ecdsa.PrivateKey, 0)
	sk := make([]*bls.SecretKey, 0)

	for i := 0; i < num; i++ {
		var blsKey bls.SecretKey
		blsKey.SetByCSPRNG()
		ecdsaKey, _ := crypto.GenerateKey()
		pk = append(pk, ecdsaKey)
		sk = append(sk, &blsKey)
	}
	return pk, sk
}

// GenerateCbftNode returns a list of CbftNode for testing.
func GenerateCbftNode(num int) ([]*ecdsa.PrivateKey, []*bls.SecretKey, []params.CbftNode) {
	pk, sk := GenerateKeys(num)
	nodes := make([]params.CbftNode, num)
	for i := 0; i < num; i++ {

		nodes[i].Node = *enode.NewV4(&pk[i].PublicKey, nil, 0, 0)
		nodes[i].BlsPubKey = *sk[i].GetPublicKey()

	}
	return pk, sk, nodes
}

// CreateCBFT returns a new CBFT for testing.
func CreateCBFT(pk *ecdsa.PrivateKey, sk *bls.SecretKey, period uint64, amount uint32) *Cbft {

	sysConfig := &params.CbftConfig{
		Period:       period,
		Amount:       amount,
		InitialNodes: []params.CbftNode{},
	}

	optConfig := &ctypes.OptionsConfig{
		NodePriKey:        pk,
		Id:                enode.PubkeyToIDV4(&pk.PublicKey),
		BlsPriKey:         sk,
		MaxQueuesLimit:    1000,
		BlacklistDeadline: 1,
	}

	ctx := node.NewServiceContext(&node.Config{DataDir: ""}, nil, new(event.TypeMux), nil)

	return New(sysConfig, optConfig, ctx.EventMux, ctx)
}

func CreateGenesis(db ethdb.Database) (core.Genesis, *types.Block) {
	var (
		gspec = core.Genesis{
			Config: chainConfig,
			Alloc:  core.GenesisAlloc{},
		}
	)
	gspec.Alloc[xcom.PlatONFundAccount()] = core.GenesisAccount{
		Balance: xcom.PlatONFundBalance(),
	}
	gspec.Alloc[cvm.RewardManagerPoolAddr] = core.GenesisAccount{
		Balance: twoBillion,
	}
	block := gspec.MustCommit(db)
	return gspec, block
}

// CreateBackend returns a new Backend for testing.
func CreateBackend(engine *Cbft, nodes []params.CbftNode) (*core.BlockChain, *core.BlockChainCache, *core.TxPool, consensus.Agency) {

	var db = rawdb.NewMemoryDatabase()
	gspec, _ := CreateGenesis(db)

	chain, _ := core.NewBlockChain(db, nil, gspec.Config, engine, vm.Config{}, nil)
	cache := core.NewBlockChainCache(chain)
	txpool := core.NewTxPool(testTxPoolConfig, chainConfig, cache)

	return chain, cache, txpool, validator.NewStaticAgency(nodes)
}

// CreateValidatorBackend returns a new ValidatorBackend for testing.
func CreateValidatorBackend(engine *Cbft, nodes []params.CbftNode) (*core.BlockChain, *core.BlockChainCache, *core.TxPool, consensus.Agency) {
	var (
		db    = rawdb.NewMemoryDatabase()
		gspec = core.Genesis{
			Config: chainConfig,
			Alloc:  core.GenesisAlloc{},
		}
	)
	balanceBytes, _ := hexutil.Decode("0x2000000000000000000000000000000000000000000000000000000000000")
	balance := big.NewInt(0)
	gspec.Alloc[testAddress] = core.GenesisAccount{
		Code:    nil,
		Storage: nil,
		Balance: balance.SetBytes(balanceBytes),
		Nonce:   0,
	}
	gspec.MustCommit(db)

	chain, _ := core.NewBlockChain(db, nil, gspec.Config, engine, vm.Config{}, nil)
	cache := core.NewBlockChainCache(chain)
	txpool := core.NewTxPool(testTxPoolConfig, chainConfig, cache)

	return chain, cache, txpool, validator.NewInnerAgency(nodes, chain, int(engine.config.Sys.Amount), int(engine.config.Sys.Amount)*2)
}

// TestCBFT for testing.
type TestCBFT struct {
	engine *Cbft
	chain  *core.BlockChain
	cache  *core.BlockChainCache
	txpool *core.TxPool
	agency consensus.Agency
}

// Start turns on the cbft for testing.
func (t *TestCBFT) Start() error {
	return t.engine.Start(t.chain, t.cache, t.txpool, t.agency)
}

// MockNode returns a new TestCBFT for testing.
func MockNode(pk *ecdsa.PrivateKey, sk *bls.SecretKey, nodes []params.CbftNode, period uint64, amount uint32) *TestCBFT {
	engine := CreateCBFT(pk, sk, period, amount)

	chain, cache, txpool, agency := CreateBackend(engine, nodes)
	return &TestCBFT{
		engine: engine,
		chain:  chain,
		cache:  cache,
		txpool: txpool,
		agency: agency,
	}
}

// MockValidator returns a new TestCBFT for testing.
func MockValidator(pk *ecdsa.PrivateKey, sk *bls.SecretKey, nodes []params.CbftNode, period uint64, amount uint32) *TestCBFT {
	engine := CreateCBFT(pk, sk, period, amount)

	chain, cache, txpool, agency := CreateValidatorBackend(engine, nodes)
	return &TestCBFT{
		engine: engine,
		chain:  chain,
		cache:  cache,
		txpool: txpool,
		agency: agency,
	}
}

// NewEngineManager returns a list of EngineManager and Id.
func NewEngineManager(cbfts []*TestCBFT) ([]*network.EngineManager, []enode.ID) {
	nodeids := make([]enode.ID, 0)
	engines := make([]*network.EngineManager, 0)
	for _, c := range cbfts {
		engines = append(engines, c.engine.network)
		nodeids = append(nodeids, c.engine.config.Option.Id)
	}
	return engines, nodeids
}

// Mock4NodePipe returns a list of TestCBFT for testing.
func Mock4NodePipe(start bool) []*TestCBFT {
	pk, sk, cbftnodes := GenerateCbftNode(4)
	nodes := make([]*TestCBFT, 0)
	for i := 0; i < 4; i++ {
		node := MockNode(pk[i], sk[i], cbftnodes, 20000, 10)

		nodes = append(nodes, node)
		//fmt.Println(i, node.engine.config.Option.Id.TerminalString())
		nodes[i].Start()
	}

	netHandler, nodeids := NewEngineManager(nodes)

	network.EnhanceEngineManager(nodeids, netHandler)
	if start {
		for i := 0; i < 4; i++ {
			netHandler[i].Testing()
		}
	}
	return nodes
}
