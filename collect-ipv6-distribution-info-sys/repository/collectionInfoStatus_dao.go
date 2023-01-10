package repository

import (
	"collect-ipv6-distribution-info-sys/model"
	"context"
	"go.mongodb.org/mongo-driver/mongo"
)

type CollectionStatusDao struct {
	*mongo.Collection
}

func NewCollectionStatusDao() *CollectionStatusDao {
	return &CollectionStatusDao{NewDBConnection("t_collection_status")}
}

func (dao *CollectionStatusDao) saveStatus(ctx context.Context, doc *model.CollectionInfoStatus) (operationId interface{}, err error) {

	res, err := dao.Collection.InsertOne(ctx, doc)
	operationId = res.InsertedID

	return
}
