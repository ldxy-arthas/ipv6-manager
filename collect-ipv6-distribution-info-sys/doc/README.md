## 需求简要分析
从DHCP服务器收集信息并发送到Mongodb数据库中，当用户主动要求收集ipv6信息时，调用controller接口完成

## 细节分析
具体实现分析
### ipv6收集分析
用户请求执行收集命令 <br>
-> <br>
controller <br>
-> <br>
service.RunOnceIpv6CollectionAction （service中调用runShellScript, parsingRunRes调用shell.Run()）
-> <br>
在运行shell命令结束之后，保存此次执行shell脚本的状态（collectionInfoStatus）到数据库中
-> <br>

系统定时任务<br>
task <br>
-> <br>
ipv6-service <br>
-> <br>
InsertInfo <br>
-> （service中调用runShellScript, parsingRunRes调用shell.Run()）
-> <br>
在运行shell命令结束之后，保存此次执行shell脚本的状态（collectionInfoStatus）到数据库中

### 系统运行状态检查
提供给ipv6_manager调用，确保收集数据服务正常可用。<br>
数据库连接正常，系统运行正常
