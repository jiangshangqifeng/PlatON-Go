#!/usr/bin/env python3
# -*- coding: utf-8 -*-
#   @Time    : 2020/2/13 20:40
#   @Author  : PlatON-Developer
#   @Site    : https://github.com/PlatONnetwork/
import time

import math

from tests.lib import get_pledge_list, Decimal
from tests.lib.client import Client
from tests.lib.config import StakingConfig


def test_deploy(global_test_env):
    global_test_env.deploy_all("./deploy/tmp/genesis_0.8.0.json")
    node1 = global_test_env.get_all_nodes()[0]
    node2 = global_test_env.get_all_nodes()[1]
    node1.admin.addPeer("enode://80053b99102f118b99006c436b5e63513d405ba560498b3debc0ea038b0338c01ce7c1a0447ec7b41400f20b7706ed68f3267c226170cf406e066e5bbe3445b2@10.10.8.191:16789")
    node2.admin.addPeer("enode://80053b99102f118b99006c436b5e63513d405ba560498b3debc0ea038b0338c01ce7c1a0447ec7b41400f20b7706ed68f3267c226170cf406e066e5bbe3445b2@10.10.8.191:16789")
    node1.admin.addPeer("enode://0bf7e804ad9c518a72d2ae698d453946109d2fa4547aa2c28288a80868aad1d6042fcdcfd7cb38cebb849846da5204226a113176a93a777133ea4340dff0a274@10.10.8.192:16789")
    node2.admin.addPeer("enode://0bf7e804ad9c518a72d2ae698d453946109d2fa4547aa2c28288a80868aad1d6042fcdcfd7cb38cebb849846da5204226a113176a93a777133ea4340dff0a274@10.10.8.192:16789")
    n = 0
    print(node1.admin.nodeInfo)
    print(node1.debug.economicConfig())
    print(node1.eth.getBalance("0x2e95E3ce0a54951eB9A99152A6d5827872dFB4FD"))

    while n < 30:
        print(node1.block_number, "node1")
        print(node2.block_number, "node2")
        time.sleep(1)
        n += 1

def test_01(global_test_env):
    node_id = '3058ac78b0a05637218a417e562daaca2d640afb3d142ada765650cc0bed892d91d6e8128df0a59397ea051a2d91af5b532866f411811f4fd46de068ad0e168d'
    node1 = global_test_env.find_node_by_node_id(node_id)
    print(node1.node_mark)
