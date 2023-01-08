package serializer

import "collect-ipv6-distribution-info-sys/model"

type SysStatus struct {
	DbStatus  string `json:"db_status"`
	SysStatus string `json:"sys_status"`
}

func BuilderSysStatus(status *model.SysStatus) *SysStatus {

	return &SysStatus{
		DbStatus:  status.DbStatus,
		SysStatus: status.SysStatus,
	}
}
