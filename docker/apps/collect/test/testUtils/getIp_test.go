package testUtils

import (
	"collect-ipv6-distribution-info-sys/pkg/util/common"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"strings"
	"testing"

	"github.com/sirupsen/logrus"
)

// type ipInfo struct {
// 	country  string
// 	location string
// 	local    string
// 	City     string
// 	Area     string
// 	Isp      string
// 	Ip       string
// 	Code     int
// 	Desc     string
// }
type ipInfo struct {
	Code string
	Data struct {
		Myip string
		IP   struct {
			Query string
			Start string
			End   string
		}
		Location string
		Country  string
		Local    string
	}
}

func TestGetOutBoundIP(t *testing.T) {
	ip, err := common.GetOutBoundIP()
	if err != nil {
		logrus.Errorf("获取本机ip失败, %s\n", err)
	}

	fmt.Println("获取成功", ip)
}

func Test2(t *testing.T) {
	//url := "https://ip.useragentinfo.com/ipv6/2402:4e00:40:40::2:3b6"
	//ipv6 := "2402:4e00:40:40::2:3b6"
	url2 := "https://ip.zxinc.org/api.php?type=json&ip=2402:4e00:40:40::2:3b6"
	resp, error := http.Get(url2)
	if error != nil {
		fmt.Printf("query occur a error :%v ", error)
		os.Exit(1)
	}
	data, error := ioutil.ReadAll(resp.Body)
	resp.Body.Close()
	if error != nil {
		fmt.Printf("read data from response error :%v", error)
		os.Exit(1)
	}
	ip := ipInfo{}
	// fmt.Printf("result is :%s\n", data)
	json.Unmarshal(data, &ip)
	// fmt.Printf(ip.Data.Country)
	for _, val := range strings.Split(ip.Data.Country, "\t") {
		fmt.Println(val)
	}

}
