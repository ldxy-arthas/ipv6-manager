package testDB

import (
	"collect-ipv6-distribution-info-sys/conf"
	"collect-ipv6-distribution-info-sys/model"
	"collect-ipv6-distribution-info-sys/repository"
	"context"
	"fmt"
	"testing"
	"time"
)

// init
func init() {
	conf.Init()
	repository.Init()
}

func TestInitDBConnection(t *testing.T) {

	err := repository.CheckDbConnection()
	if err != nil {
		fmt.Printf("db connection failed: err:%v", err)
	}

	fmt.Printf("db connection success!")
}

func TestInsertData(t *testing.T) {

	var ipv6Data = model.Ipv6Info{
		Id:         "123455",
		RegionId:   100,
		IsUsed:     1,
		CreateTime: time.Now().Format("2006-01-02 15:04:05"),
		UpdateTime: time.Now().Format("2006-01-02 15:04:05"),
	}

	var ctx context.Context

	ipv6CollectionDao := repository.NewIpv6Dao()
	info, err := ipv6CollectionDao.SaveInfo(ctx, &ipv6Data)
	if err != nil {
		fmt.Printf("测试数据插入失败， err:%v", err)
		return
	}

	fmt.Printf("测试数据插入结果：%v", info)
}

func TestSaveIpv6CollectionStatus(t *testing.T) {

	var collStatus = model.CollectionInfoStatus{
		Id:             "1234",
		CollectionTime: time.Now().Format("2006-01-02 15:04:05"),
		Status:         "true",
		CollectionIp:   "test-ip",
		Content:        "",
	}

	var ctx context.Context

	collectionStatusDao := repository.NewCollectionStatusDao()
	id, _ := collectionStatusDao.SaveStatus(ctx, &collStatus)

	fmt.Println(id)

}
