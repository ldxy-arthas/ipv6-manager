package common

import "log"

func ErrorUtil[T any](v T, err error) T {

	if err != nil {
		log.Panic(err)
	}
	return v
}
