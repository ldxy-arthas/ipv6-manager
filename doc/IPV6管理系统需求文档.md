# IPV6管理系统需求文档

## 功能需求

>  通过机器把ipv6分配下去， 分配给了谁， 使用没有使用（在线情况），检测。 基于ipv6的网站检测（是不是网站支持ipv6访问）

### ipv6 分配

> 查看ipv6在一定区域内的分配情况

- 前端

  展示ipv6的分配信息

- 后端

  获取ipv6的分配情况

### ipv6 跟踪

> 在系统或者管理员分配ipv6完成之后，监控ipv6在该设备上的使用情况

- 前端

  展示ipv6的使用情况

- 后端

  获取ipv6在设备上的使用情况

### ipv6检测

> 判断网站是否支持ipv6

- 前端

  检测输入的指定网站是否ipv6

- 后端

  检测输入的指定网站是否ipv6

## 开发环境

数据库：mongodb

boot：spring boot 3.0

jdk：jdk17

依赖管理：maven

开发工具：Intellij IDEA

部署系统：Open Euler

版本管理：github

接口测试工具：Postman

前端：vue3.0 ***（前端开发自己选择用啥，之后补充下文档）***

## 开发规范



## gitlab使用规范

### 分支说明

| 分支                | 概述                            |
|-------------------|-------------------------------|
| master            | 主分支，发布新功能（稳定）                 |
| patch/version_x.x | 补丁分支（对应版本的bug修复）              |
| dev               | 开发分支（内部开发用的分支，不稳定，功能可用，可测试）   |
| feature           | 开发分支，开发新功能，或者修复bug，全部基于这个分支操作 |
| dev-feature       | 功能开发分支组                       |
| dev-fix           | 功能bug修复分支组                    |



> ***xxx功能的开发分支，同一功能协同开发的话，开发人员在功能分支下面再checkout自己的分支***
>
> ***pr参考阿里pr过程*，使用sourceTree**

### 分支规范

#### 分支使用规范

* `master` `release/*` `dev` `feature` 四个分支是受保护分支，不允许直接推送代码，需要按照以下规范合并

* 开发新功能，在 `dev-feature/` 组下从 `feature` 分支checkout一个最新版本出来，如 `dev-feature/xxx`，基于 feature 进行功能开发，代码可用时，合并至 `feature` 分支


* 开发中修复bug，在 `dev-fix/` 组下从 `feature` 功能开发分支checkout一个最新版本出来，如 `dev-fix/xxx`，基于已push到功能分支的代码进行修复，修复完成后，合并到 `feature`
  功能分支

* 一批 feature 开发完成，测试通过后，将 `feature` 功能分支合并到 `dev` 分支

* 在 `dev` 某次提交稳定可用时，给 `dev` 分支打tag，然后将当前 tag checkout 出来，以发布的版本号命名，作为新的分支 push 到 release 组下

* 一批版本的 release 稳定后，将 release 最新版本的分支合并到 `master` 作为主分支留存

#### 基于分支开发和测试

1. checkout `origin/feature` 分支到本地 `feature`
2. 从本地 `feature` 分支 checkout 新分支出来，如 `dev-feature/xxx` （xxx为功能名字）
3. 在 `dev-feature/xxx` 分支上进行新功能开发
4. 开发完成后，切换到 `feature` 分支，pull 到最新，然后切换回 `dev-feature/xxx` 分支
5. 然后将 `feature` 合并到 `dev-feature/xxx` 分支（遇到冲突要手动合并）
6. 确认当前在 `dev-feature/xxx` 分支，进行代码测试和功能验证，保证自己的功能正常，顺便看下整个系统有没有其他功能异常
7. push `dev-feature/xxx` 分支到服务器
8. （在网页版gitlab）提交合并请求，源分支应该是 `dev-feature/xxx` 分支，目标分支是 `feature` 分支
9. 等待审核完成即可

## 前后端接口文档

### 后台接口文档

> 使用 `knife4j` 生成的接口文档  **http://网关地址:网关端口/doc.html**

### Postman接口文档

## 数据库字段

### 公共字段

### ipv6类（T_ipv6_info）

| 字段          | 概述     | 类型     |
|:------------|--------|--------|
| ipv6_id | ipv6主键 | string |
| region_id  | 地区主键   | string |
| ipv6        | ipv6编号 | string |
| is_used | 标记此地址是否被使用，如使用，为具体的mac地址，默认为0 | string |
|create_time|创建时间| date|
|update_time|更新时间| date|


### 地区类(T_region)

#### 描述：地区详情

| 字段               | 概述   | 类型     |
|------------------|------|--------|
| region_id       | 地区主键 | string |
| region_name     | 地区名字 | string |
| region_level_id | 地区级别（该地区的级别比如：市，州，县，区，其他） | string |
|region_number|地区编号| string|
|create_time|创建时间| date|
|update_time|更新时间| date|

### 地区级别(T_region_level)

#### 描述：地区级别 供以后前端配置

| 字段               | 概述     | 类型     |
|------------------|--------|--------|
| region_level_id | 地区级别主键 | string |
| region_name     | 地区级别名字 该地区级别的名字比如：市，州，县，区，其他）| string |
|create_time|创建时间| date|
|update_time|更新时间| date|

### 网站查询记录表(T_website_info)

| 字段               | 概述     | 类型     |
|------------------|--------|--------|
| website_info_id | 网站查询主键 | string |
| website_Info_address | 网站地址（存网站的域名） | string |
| ipv6_status | 是否能够使用ipv6，是为1，否为0 | int |
|is_ipv6 |    是否用了ipv6，已经使用为1，未使用为0    |    boolean   |
|create_time|创建时间| date|
|update_time|更新时间| date|

### 用户表(T_user)

| 字段        | 概述           | 类型   |
| ----------- | -------------- | ------ |
| user_id     | 用户id         | string |
| username    | 用户名         | string |
| password    | 用户密码       | string |
| region      | 用户所属的地区 | string |
| create_time | 用户创建时间   | Date   |
|update_time|更新时间| date|

### 日志记录表（T_log）

| 字段           | 概述             | 类型   |
| -------------- | ---------------- | ------ |
| log_id         | 日志id           | string |
| user_id        | 用户id           | string |
| user_operation | 用户操作         | string |
| op_time        | 操作时间         | date   |
| hint           | 用户操作备注信息 | string |

