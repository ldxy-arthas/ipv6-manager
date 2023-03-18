package testUtils

import (
	"fmt"
	"regexp"
	"testing"
)

func TestStringSplit(t *testing.T) {

	/*
		lease 10.41.3.100 {
			starts 6 2021/02/27 01:12:09;
			ends 6 2021/02/27 01:54:09;
			cltt 6 2021/02/27 01:54:09;
			binding state free;
			hardware ethernet d8:db:8a:77:e3:1c;
		}
	*/

	var data = `
		lease 10.41.3.100 {
			starts 6 2021/02/27 01:12:09;
			ends 6 2021/02/27 01:54:09;
			cltt 6 2021/02/27 01:54:09;
			binding state free;
			hardware ethernet d8:db:8a:77:e3:1c;
		}
			`

	ipReg := regexp.MustCompile(`lease (.*?) {`)
	startsTimeReg := regexp.MustCompile(`starts (.*?);`)
	endsTimeReg := regexp.MustCompile("ends (.*?);")
	macAddressReg := regexp.MustCompile("hardware ethernet (.*?);")

	ip := ipReg.FindAllStringSubmatch(data, -1)
	startsTime := startsTimeReg.FindAllStringSubmatch(data, -1)
	endsTime := endsTimeReg.FindAllStringSubmatch(data, -1)
	macAddress := macAddressReg.FindAllStringSubmatch(data, -1)

	fmt.Println(ip)
	fmt.Println(startsTime)
	fmt.Println(endsTime)
	fmt.Println(macAddress)
}
