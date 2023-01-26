package repository

import (
	"collect-ipv6-distribution-info-sys/conf"
	logger "collect-ipv6-distribution-info-sys/pkg/util/log"
	"context"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

var (
	_client *mongo.Client
)

func Init() {

	dsn := conf.Db + "://" + conf.DbUser + ":" +
		conf.DbPassword + "@" + conf.DbHost + ":" + conf.DbPort + "/" + conf.DbName
	logger.LogrusObj.Infof("db url: %s", dsn)

	clientOptions := options.Client().ApplyURI(dsn)
	client, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		logger.LogrusObj.Fatalf("init db connection failed: err:%v", err)
	}

	_client = client

	err = CheckDbConnection()
	if err != nil {
		return
	}

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
