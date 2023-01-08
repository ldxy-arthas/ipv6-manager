package model

import "gorm.io/gorm"

type CollectionInfoStatus struct {
	gorm.Model
	Id             string `gorm:"not null"`
	collectionTime string
	Status         string
	CollectionIp   string
	SendStatus     string
}
