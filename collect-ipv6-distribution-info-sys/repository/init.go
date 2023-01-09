package repository

import (
	"collect-ipv6-distribution-info-sys/conf"
	"context"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
)

var (
	_client *mongo.Client
)

func Init() {

	clientOptions := options.Client().ApplyURI(conf.Db + "://" + conf.DbHost + ":" + conf.DbPort)
	client, err := mongo.Connect(context.TODO(), clientOptions)
	if err != nil {
		log.Fatal(err)
	}

	err = CheckDbConnection()
	if err != nil {
		return
	}

	_client = client

}

func CheckDbConnection() (err error) {
	// 检测连接
	err = _client.Ping(context.TODO(), nil)
	return
}

// NewDBConnection 创建一个数据库连接
func NewDBConnection(collectionName string) *mongo.Collection {

	return _client.Database(conf.DbName).Collection(collectionName)
}
