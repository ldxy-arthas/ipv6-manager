package shell

import (
	"bytes"
	"collect-ipv6-distribution-info-sys/conf"
	logger "collect-ipv6-distribution-info-sys/pkg/util/log"
	"io"
	"os"
	"os/exec"
)

// Run 采取同步执行方式，阻塞等待执行完成
func Run() (outStr, errStr string) {

	var stdoutBuf, stderrBuf bytes.Buffer
	cmd := exec.Command(conf.Platform, "/C", conf.Command)

	stdoutIn, _ := cmd.StdoutPipe()
	stderrIn, _ := cmd.StderrPipe()

	var errStdout, errStderr error
	stdout := io.MultiWriter(os.Stdout, &stdoutBuf)
	stderr := io.MultiWriter(os.Stderr, &stderrBuf)

	go func() {
		_, errStdout = io.Copy(stdout, stdoutIn)
	}()
	go func() {
		_, errStderr = io.Copy(stderr, stderrIn)
	}()

	// run command
	errStderr = cmd.Start()

	err := cmd.Wait()
	if err != nil {
		logger.LogrusObj.Fatalf("cmd.Run() failed with %s\n", err)
	}
	if errStdout != nil || errStderr != nil {
		logger.LogrusObj.Fatal("failed to capture stdout or stderr\n")
	}

	outStr, errStr = string(stdoutBuf.Bytes()), string(stderrBuf.Bytes())
	logger.LogrusObj.Infof("命令执行结果: 输出：%s, err: %s", outStr, errStr)

	return
}
