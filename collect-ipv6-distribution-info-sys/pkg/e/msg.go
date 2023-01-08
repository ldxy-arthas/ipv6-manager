package e

var MsgFlags = map[int]string{
	Success:       "ok",
	Error:         "fail",
	InvalidParams: "参数错误",

	// 数据库错误类型
	DbStatusError: "数据库状态异常",

	// 系统状态错误
	SysStatusError: "系统运行异常",

	// ipv6错误
	CollectionIpv6Error:     "收集Ipv6信息异常",
	SendIpv6FailedError:     "发送Ipv6信息异常",
	SaveIpv6CollectionError: "保存ipv6收集信息异常",
}

// GetMsg 获取状态码对应的信息
func GetMsg(code int) string {
	msg, ok := MsgFlags[code]
	if !ok {
		return MsgFlags[Error]
	}

	return msg
}
