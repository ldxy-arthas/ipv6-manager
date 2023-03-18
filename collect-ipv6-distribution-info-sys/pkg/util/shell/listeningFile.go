package shell

import (
	"collect-ipv6-distribution-info-sys/conf"
	"fmt"
	"github.com/OhYee/goutils/file"
	"github.com/OhYee/rainbow/log"
	"os"
	"path"
)

// 初始化文件监听对象
func init() {

	_, err := PathExists(conf.LogPath)
	if err != nil {
		fmt.Println("日志文件读取错误，请检查文件路径:", err)
		panic(err)
	}

}

// Run 监听文件新增的日志数据
func Run() (outStr, errStr string) {

	logFile := path.Join(conf.LogPath)

	fmt.Printf(logFile)

	w, old, err := file.NewWatcher(logFile)
	if err != nil {
		//log.Error.Println(errors.ShowStack(err))
		errStr = err.Error()
		os.Exit(1)
	}

	log.Info.Println(old)
	err = w.Watch(func(s string) {
		// outStr = fmt.Sprintf("%s%s", outStr, s)
		errStr = err.Error()
	})
	if err != nil {
		// log.Error.Println(errors.ShowStack(err))
		errStr = err.Error()
	}
	defer w.Close()
	select {}

	return

}

// PathExists 判断一个文件或文件夹是否存在
// 输入文件路径，根据返回的bool值来判断文件或文件夹是否存在
func PathExists(path string) (bool, error) {
	_, err := os.Stat(path)
	if err == nil {
		return true, nil
	}
	if os.IsNotExist(err) {
		return false, nil
	}
	return false, err
}
