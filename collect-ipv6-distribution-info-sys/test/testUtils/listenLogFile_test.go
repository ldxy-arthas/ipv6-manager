package testUtils

import (
	"collect-ipv6-distribution-info-sys/conf"
	"fmt"
	"github.com/OhYee/goutils/file"
	"github.com/OhYee/rainbow/errors"
	"github.com/OhYee/rainbow/log"
	"os"
	"path"
	"testing"
)

func init() {
	conf.Init()
}

func TestListenLogFile(t *testing.T) {
	logFile := path.Join("../", "testLog.log")

	fmt.Printf(logFile)

	w, old, err := file.NewWatcher(logFile)
	if err != nil {
		log.Error.Println(errors.ShowStack(err))
		os.Exit(1)
	}

	log.Info.Println("old: = " + old)
	err = w.Watch(func(s string) {
		log.Info.Println("new: = " + s)
	})
	if err != nil {
		log.Error.Println(errors.ShowStack(err))
	}
	defer w.Close()
	select {}
}
