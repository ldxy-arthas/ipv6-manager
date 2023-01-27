package service

import (
	"collect-ipv6-distribution-info-sys/model"
	"collect-ipv6-distribution-info-sys/pkg/e"
	"collect-ipv6-distribution-info-sys/pkg/enum"
	"collect-ipv6-distribution-info-sys/pkg/util/common"
	"collect-ipv6-distribution-info-sys/pkg/util/shell"
	"collect-ipv6-distribution-info-sys/repository"
	"collect-ipv6-distribution-info-sys/serializer"
	"context"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"strings"
	"time"

	"github.com/sirupsen/logrus"
)

type Ipv6CollectionService struct {
}

func (service Ipv6CollectionService) RunOnceIpv6CollectionAction(ctx context.Context) serializer.Response {

	code := e.Success

	str := parsingRunRes(ctx)

	service.InsertInfo(ctx)

	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data:   str,
	}
}

func (service Ipv6CollectionService) InsertInfo(ctx context.Context) serializer.Response {

	parsingRunRes(ctx)

	var ipv6Collection *model.Ipv6Info
	code := e.Success

	ipv6CollectionDao := repository.NewIpv6Dao()
	operaId, err := ipv6CollectionDao.InsertOne(ctx, ipv6Collection)
	if err != nil {
		code = e.SaveIpv6CollectionError
		logrus.Errorf("insert failed err:%s", err)
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
			Data:   "保存ipv6信息失败！",
		}
	}

	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data:   operaId,
	}

}

func parsingRunRes(ctx context.Context) (outStr string) {
	outStr, errStr := shell.Run()
	fmt.Printf("\nout:\n%s\nerr:\n%s\n", outStr, errStr)

	// 保存从dhcp服务器收集ipv6信息时的状态信息（成功或者失败状态，执行shell脚本的响应内容）
	status, err := checkCollectionStatus(ctx, outStr, errStr)

	fmt.Printf("%v \t %v", status, err)

	return outStr
}

func checkCollectionStatus(ctx context.Context, outStr, errStr string) (status *model.CollectionInfoStatus, err error) {

	var collectionStatus model.CollectionInfoStatus
	ip, err := common.GetOutBoundIP()

	if errStr != "" {
		collectionStatus.CollectionIp = ip
		collectionStatus.CollectionTime = time.Now().Format(enum.TimeStringFormat)
		collectionStatus.Status = enum.FAILED
		collectionStatus.Content = errStr
	}

	collectionStatus.CollectionIp = ip
	collectionStatus.CollectionTime = time.Now().Format(enum.TimeStringFormat)
	collectionStatus.Status = enum.SUCCESS
	collectionStatus.Content = outStr

	// 保存到数据中
	var collectionService CollectionInfoStatusService
	CollectionInfoStatusService.SaveStatus(collectionService, ctx, &collectionStatus)

	return
}

func getRegion(ip string) ipInfo {
	url := "https://ip.zxinc.org/api.php?type=json&ip="
	url = url + ip
	resp, error := http.Get(url)
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
	var info ipInfo
	json.Unmarshal(data, &info)
	var regin string
	for _, val := range strings.Split(info.Data.Country, "\t") {
		regin += val
	}
	info.Data.Country = regin

	return info
}

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
