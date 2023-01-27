package dto

// https://ip.zxinc.org/api.php?type=json返回的参数
type ipInfo struct {
	Code string
	Data struct {
		Myip string
		IP   struct {
			Query string
			Start string
			End   string
		}
		Location string
		Country  string
		Local    string
	}
}
