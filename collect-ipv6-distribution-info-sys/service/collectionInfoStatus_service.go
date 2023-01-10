package service

import (
	"collect-ipv6-distribution-info-sys/model"
	"collect-ipv6-distribution-info-sys/pkg/e"
	"collect-ipv6-distribution-info-sys/repository"
	"collect-ipv6-distribution-info-sys/serializer"
	"context"
	"github.com/sirupsen/logrus"
)

type CollectionInfoStatusService struct {
}

func (service CollectionInfoStatusService) SaveStatus(ctx context.Context, status *model.CollectionInfoStatus) serializer.Response {

	code := e.Success

	collectionStatusDao := repository.NewCollectionStatusDao()
	operaId, err := collectionStatusDao.InsertOne(ctx, status)

	if err != nil {
		code = e.CollectionIpv6StatusError
		logrus.Errorf("insert failed err:%s", err)
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
			Data:   "保存ipv6收集状态失败！",
		}
	}

	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data:   operaId,
	}

}
