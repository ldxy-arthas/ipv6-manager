package common

import (
	"collect-ipv6-distribution-info-sys/conf"
	"fmt"
	"net"
	"strings"
)

// IsProd 是否是正式环境
func IsProd() bool {
	return conf.AppModel == "prod"
}

func GetOutBoundIP() (ip string, err error) {
	conn, err := net.Dial("udp", "8.8.8.8:53")
	if err != nil {
		fmt.Println(err)
		return
	}
	localAddr := conn.LocalAddr().(*net.UDPAddr)
	fmt.Println(localAddr.String())
	ip = strings.Split(localAddr.String(), ":")[0]
	return
}
