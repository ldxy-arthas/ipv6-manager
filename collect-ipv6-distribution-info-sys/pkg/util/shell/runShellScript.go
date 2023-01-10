package shell

import (
	"bytes"
	"collect-ipv6-distribution-info-sys/conf"
	"io"
	"log"
	"os"
	"os/exec"
)

func Run() (outStr, errStr string) {

	var stdoutBuf, stderrBuf bytes.Buffer
	cmd := exec.Command(conf.Command)

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

	err := cmd.Wait()
	if err != nil {
		log.Fatalf("cmd.Run() failed with %s\n", err)
	}
	if errStdout != nil || errStderr != nil {
		log.Fatal("failed to capture stdout or stderr\n")
	}

	outStr, errStr = string(stdoutBuf.Bytes()), string(stderrBuf.Bytes())

	return
}