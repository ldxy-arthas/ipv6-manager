package conf

import (
	"fmt"
	"gopkg.in/ini.v1"
)

// 读取配置文件

// 定义全局变量
var (
	AppModel string
	HttpPort string
	Host     string

	Db         string
	DbHost     string
	DbPort     string
	DbUser     string
	DbPassword string
	DbName     string

	Command  string
	Platform string
)

// Init 初始化配置
func Init() {
	// 本地读取环境变量  注意：此路径是从main.go开始算起的路径
	file, err := ini.Load("./conf/config.ini")
	if err != nil {
		// 处理读取配置文件异常
		// panic 直译为 运行时恐慌 当panic被抛出异常后，如果我们没有在程序中添加任何保护措施的话，程序就会打印出panic的详细情况之后，终止运行
		fmt.Println("配置文件读取错误，请检查文件路径:", err)
	}

	//读取配置
	LoadServer(file)
	LoadMongodb(file)
	LoadSystemShell(file)
}

// LoadMongodb 加载数据库配置信息
func LoadMongodb(file *ini.File) {
	Db = file.Section("mongo").Key("DB").String()
	DbHost = file.Section("mongo").Key("DbHost").String()
	DbPort = file.Section("mongo").Key("DbPort").String()
	DbUser = file.Section("mongo").Key("DbUser").String()
	DbPassword = file.Section("mongo").Key("DbPassword").String()
	DbName = file.Section("mongo").Key("DbName").String()
}

// LoadServer 加载服务配置信息
func LoadServer(file *ini.File) {
	AppModel = file.Section("service").Key("AppMode").String()
	HttpPort = file.Section("service").Key("HttpPort").String()
	Host = file.Section("service").Key("Host").String()
}

// LoadSystemShell 加载shell配置信息
func LoadSystemShell(file *ini.File) {
	Command = file.Section("system").Key("Command").String()
	Platform = file.Section("system").Key("Platform").String()
}
