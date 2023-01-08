package main

import (
	"collect-ipv6-distribution-info-sys/conf"
	"collect-ipv6-distribution-info-sys/pkg/util/common"
	"collect-ipv6-distribution-info-sys/repository"
	"collect-ipv6-distribution-info-sys/routes"
	"collect-ipv6-distribution-info-sys/scheduler"
)

func main() {

	// init configuration file
	conf.Init()

	// init db
	repository.Init()

	// Starting a scheduled task
	if common.IsProd() {
		scheduler.Start()
	}

	// init router
	router := routes.NewRouter()
	_ = router.Run(conf.HttpPort)

}
