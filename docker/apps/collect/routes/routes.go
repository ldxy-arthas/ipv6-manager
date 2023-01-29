package routes

import (
	api "collect-ipv6-distribution-info-sys/api/v1"
	"collect-ipv6-distribution-info-sys/middleware"
	"github.com/gin-gonic/gin"
)

func NewRouter() *gin.Engine {

	r := gin.Default()

	r.Use(middleware.Cors())
	v1 := r.Group("/api/v1")
	{
		v1.GET("ping", func(c *gin.Context) {
			c.JSON(200, "success")
		})
		// api接口操作
		v1.GET("sys/checkSys", api.SysStatus)
		v1.GET("ipv6/collection", api.RunIpv6CollectionCmd)

	}

	return r
}
