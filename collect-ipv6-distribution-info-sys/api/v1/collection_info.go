package v1

import (
	"collect-ipv6-distribution-info-sys/pkg/util/log"
	"collect-ipv6-distribution-info-sys/serializer"
	"collect-ipv6-distribution-info-sys/service"
	"github.com/gin-gonic/gin"
	"net/http"
)

func RunIpv6CollectionCmd(c *gin.Context) {

	var collectionService service.Ipv6CollectionService

	if err := c.ShouldBind(&collectionService); err == nil {
		res := collectionService.RunOnceIpv6CollectionAction(c)
		c.JSON(200, res)
	} else {
		log.LogrusObj.Infof("System status err: %s", err)
		c.JSON(http.StatusBadRequest, serializer.ErrorResponse(err))
	}

}
