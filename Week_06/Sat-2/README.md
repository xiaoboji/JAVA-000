四张表
1. user-用户表
2. product-商品表
3. order-订单表
4. orderitem-订单商品表
---

-- shop.`user` definition

CREATE TABLE `user` (
  `userid` varchar(64) NOT NULL COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `role` int(11) NOT NULL COMMENT '用户角色 0-管理员 1-普通用户',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`userid`),
  KEY `user_userid_IDX` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表\r\n';

-- shop.product definition

CREATE TABLE `product` (
  `productid` varchar(64) NOT NULL COMMENT '商品编号',
  `snapshotid` varchar(64) NOT NULL COMMENT '快照编号',
  `productname` varchar(100) NOT NULL COMMENT '商品名称',
  `detail` text COMMENT '商品描述',
  `mainimage` varchar(100) DEFAULT NULL COMMENT '商品主图',
  `subimages` text COMMENT '商品图片集',
  `price` decimal(20,2) NOT NULL COMMENT '商品价格，保留两位小数',
  `stock` varchar(100) NOT NULL COMMENT '库存数量',
  `status` int(11) NOT NULL COMMENT '商品状态',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(100) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`productid`),
  KEY `product_snapshotid_IDX` (`snapshotid`,`productid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- shop.`order` definition

CREATE TABLE `order` (
  `orderid` varchar(64) NOT NULL COMMENT '订单编号',
  `userid` varchar(64) NOT NULL COMMENT '用户编号',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实付金额',
  `status` int(11) NOT NULL COMMENT '订单状态 0-已取消 10-未付款 20-已付款 30-已发货 40-交易成功 50-交易关闭',
  `payment_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `sendtime` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `closetime` timestamp NULL DEFAULT NULL COMMENT '交易关闭时间',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`orderid`),
  KEY `order_orderid_IDX` (`orderid`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- shop.orderitem definition

CREATE TABLE `orderitem` (
  `itemid` varchar(64) NOT NULL COMMENT '子订单编号',
  `orderid` varchar(64) NOT NULL COMMENT '订单编号',
  `userid` varchar(64) NOT NULL COMMENT '用户编号',
  `productid` varchar(64) NOT NULL COMMENT '产品编号',
  `snapshotid` varchar(64) NOT NULL COMMENT '快照id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `mainimage` varchar(100) DEFAULT NULL COMMENT '商品主图',
  `current_price` decimal(20,2) DEFAULT NULL COMMENT '当前的单价',
  `num` int(11) DEFAULT NULL COMMENT '产品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '产品总价',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间 ',
  PRIMARY KEY (`itemid`),
  KEY `orderitem_itemid_IDX` (`itemid`,`userid`,`orderid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
