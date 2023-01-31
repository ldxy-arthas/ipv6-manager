package service

import (
	"collect-ipv6-distribution-info-sys/model"
	"collect-ipv6-distribution-info-sys/pkg/e"
	logger "collect-ipv6-distribution-info-sys/pkg/util/log"
	"collect-ipv6-distribution-info-sys/repository"
	"collect-ipv6-distribution-info-sys/serializer"
)

type SysService struct {
}

func (sys SysService) CheckSysStatus() serializer.Response {

	var sysStatus model.SysStatus
	code := e.Success

	// check db status
	err := repository.CheckDbConnection()
	if err != nil {
		logger.LogrusObj.Infof("系统检测数据库 运行异常：%f", err)
		code = e.DbStatusError
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
			Error:  "数据库状态异常",
		}
	}

	sysStatus.DbStatus = "数据库状态正常！"

	// check sys
	sysStatus.SysStatus = "系统运行正常！"
	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data:   serializer.BuilderSysStatus(&sysStatus),
	}
}
