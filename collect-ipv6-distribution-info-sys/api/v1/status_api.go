package v1

import (
	"collect-ipv6-distribution-info-sys/pkg/util/log"
	"collect-ipv6-distribution-info-sys/serializer"
	"collect-ipv6-distribution-info-sys/service"
	"github.com/gin-gonic/gin"
	"net/http"
)

func SysStatus(c *gin.Context) {

	var sysStatusService service.SysService

	if err := c.ShouldBind(&sysStatusService); err == nil {
		res := sysStatusService.CheckSysStatus()
		c.JSON(200, res)
	} else {
		log.LogrusObj.Infof("System status err: %s", err)
		c.JSON(http.StatusBadRequest, serializer.ErrorResponse(err))
	}

}
