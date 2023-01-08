package model

import "gorm.io/gorm"

type SysStatus struct {
	gorm.Model
	DbStatus  string
	SysStatus string
}
