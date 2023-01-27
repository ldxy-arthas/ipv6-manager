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

	// TODO: 解析content从中获取ipv6info实体需要的数据 或者也可以在parsingRunRes中进行，直接返回实体
	//通过upv6获取地区信息(前提是要先获取ipv6)
	var info = getRegion(ipv6Collection.Ipv6)
	ipv6Collection.Region = info.Data.Country
	ipv6CollectionDao := repository.NewIpv6Dao()
	operaId, err := ipv6CollectionDao.SaveInfo(ctx, ipv6Collection)
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

// 解析 shell 命令运行之后的运行结果 outStr 可以调整为 Ipv6Info 类型
func parsingRunRes(ctx context.Context) (outStr string) {

	// TODO: 需要解析执行结果

	outStr, errStr := shell.Run()
	fmt.Printf("\nout:\n%s\nerr:\n%s\n", outStr, errStr)

	// 保存从dhcp服务器收集ipv6信息时的状态信息（成功或者失败状态，执行shell脚本的响应内容）
	status, err := checkCollectionStatus(ctx, outStr, errStr)

	fmt.Printf("%v \t %v", status, err)

	return outStr
}

// 这里是因为直接将ipv6数据存进数据库中，可能过程中存在数据丢失情况，没有使用消息中间件，所以这么处理了一下，将shell
// 命令执行时的状态存进去，然后在把ipv6信息存进去，起对照作用，相当于 数据备份
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

	// 保存到数据库中
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
