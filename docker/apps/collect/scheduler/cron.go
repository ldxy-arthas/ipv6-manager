package scheduler

import (
	"github.com/robfig/cron"
	"github.com/sirupsen/logrus"
)

func Start() {
	c := cron.New()

	// Generate RSS
	addCronFunc(c, "@every 1m", func() {
		// TODO: add service function
	})

	c.Start()
}

func addCronFunc(c *cron.Cron, sepc string, cmd func()) {
	err := c.AddFunc(sepc, cmd)
	if err != nil {
		logrus.Error(err)
	}
}
