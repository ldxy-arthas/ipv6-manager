package e

// 状态码

const (
	Success       = 200
	Error         = 500
	InvalidParams = 400

	// 数据库错误类型
	DbStatusError = 1000

	// 系统状态错误
	SysStatusError = 2000

	// ipv6错误
	CollectionIpv6Error     = 3000
	SendIpv6FailedError     = 4000
	SaveIpv6CollectionError = 5000
)
