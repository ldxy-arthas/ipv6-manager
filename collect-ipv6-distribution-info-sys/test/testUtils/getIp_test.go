package testUtils

import (
	"collect-ipv6-distribution-info-sys/pkg/util/common"
	"fmt"
	"github.com/sirupsen/logrus"
	"testing"
)

func TestGetOutBoundIP(t *testing.T) {
	ip, err := common.GetOutBoundIP()
	if err != nil {
		logrus.Errorf("获取本机ip失败, %s\n", err)
	}

	fmt.Println("获取成功", ip)
}
