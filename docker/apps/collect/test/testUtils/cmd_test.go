package testUtils

import (
	"collect-ipv6-distribution-info-sys/conf"
	"collect-ipv6-distribution-info-sys/pkg/util/shell"
	"fmt"
	"testing"
)

func init() {
	conf.Init()
}

// 命令配置查看配置文件
func TestRunCommands(t *testing.T) {

	str, errStr := shell.Run()

	fmt.Println(str)
	fmt.Println(errStr)

}
