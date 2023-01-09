package repository

import (
	"collect-ipv6-distribution-info-sys/model"
	"context"
	"go.mongodb.org/mongo-driver/mongo"
)

type Ipv6InfoDao struct {
	*mongo.Collection
}

func NewIpv6Dao() *Ipv6InfoDao {
	return &Ipv6InfoDao{NewDBConnection("t_ipv6_info")}
}

func (dao *Ipv6InfoDao) saveInfo(ctx context.Context, doc *model.Ipv6Info) (operationId interface{}, err error) {

	res, err := dao.Collection.InsertOne(ctx, doc)
	operationId = res.InsertedID

	return
}
