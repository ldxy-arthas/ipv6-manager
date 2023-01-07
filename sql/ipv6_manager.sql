/*
 Navicat Premium Data Transfer

 Source Server         : ipv6-manager
 Source Server Type    : MongoDB
 Source Server Version : 50005
 Source Host           : 112.74.111.23:27017
 Source Schema         : ipv6_manager

 Target Server Type    : MongoDB
 Target Server Version : 50005
 File Encoding         : 65001

 Date: 07/01/2023 10:39:27
*/


// ----------------------------
// Collection structure for T_region
// ----------------------------
db.getCollection("T_region").drop();
db.createCollection("T_region");

// ----------------------------
// Documents of T_region
// ----------------------------
db.getCollection("T_region").insert([ {
    _id: ObjectId("63b8db4dbb7f0000e9004f79"),
    regionName: "陇东学院",
    regionLevelId: "63b8dadabb7f0000e9004f78",
    regionNumber: "1",
    createTime: "2023-1-7 10:24",
    updateTime: "2023-1-7 10:24"
} ]);

// ----------------------------
// Collection structure for t_ipv6_info
// ----------------------------
db.getCollection("t_ipv6_info").drop();
db.createCollection("t_ipv6_info");

// ----------------------------
// Documents of t_ipv6_info
// ----------------------------
db.getCollection("t_ipv6_info").insert([ {
    _id: ObjectId("63b8d827bb7f0000e9004f73"),
    regionId: "1",
    ipv6: "fe80::735a:49db:9caf:fdba%14",
    "is_used": "0",
    createTime: "2023-1-7 10:24",
    "update_time": "2023-1-7 10:24"
} ]);

// ----------------------------
// Collection structure for t_log
// ----------------------------
db.getCollection("t_log").drop();
db.createCollection("t_log");

// ----------------------------
// Documents of t_log
// ----------------------------
db.getCollection("t_log").insert([ {
    _id: ObjectId("63b8d8dabb7f0000e9004f74"),
    userId: "1",
    userOperation: "随机分配ipv6",
    opTime: "2023-1-7 10:24",
    hint: "分配到的mac地址为：fe80::735a:49db:9caf:fdba%14; 分配状态: true"
} ]);

// ----------------------------
// Collection structure for t_region_level
// ----------------------------
db.getCollection("t_region_level").drop();
db.createCollection("t_region_level");

// ----------------------------
// Documents of t_region_level
// ----------------------------
db.getCollection("t_region_level").insert([ {
    _id: ObjectId("63b8dadabb7f0000e9004f78"),
    regionName: "庆阳市",
    createTime: "2023-1-7 10:24",
    updateTime: "2023-1-7 10:24"
} ]);

// ----------------------------
// Collection structure for t_user
// ----------------------------
db.getCollection("t_user").drop();
db.createCollection("t_user");

// ----------------------------
// Documents of t_user
// ----------------------------
db.getCollection("t_user").insert([ {
    _id: ObjectId("63b8da1ebb7f0000e9004f76"),
    username: "yuluo",
    password: "1234567qw",
    region: "1",
    createTime: "2023-1-7 10:24",
    updateTime: "2023-1-7 10:24"
} ]);

// ----------------------------
// Collection structure for t_website_info
// ----------------------------
db.getCollection("t_website_info").drop();
db.createCollection("t_website_info");

// ----------------------------
// Documents of t_website_info
// ----------------------------
db.getCollection("t_website_info").insert([ {
    _id: ObjectId("63b8daa4bb7f0000e9004f77"),
    websiteInfoAddress: "www.test.com",
    ipv6Status: "0",
    isIpv6: "1",
    createTime: "2023-1-7 10:24",
    updateTime: "2023-1-7 10:24"
} ]);
