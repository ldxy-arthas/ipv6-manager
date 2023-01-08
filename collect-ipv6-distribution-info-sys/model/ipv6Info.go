package model

import "gorm.io/gorm"

type Ipv6Info struct {
	gorm.Model
	Id         string
	RegionId   int
	Ipv6       string
	IsUsed     uint
	CreateTime string
	UpdateTime string
}
