//资讯中心按钮
function zxzxButton() {
	var url = "newsController/getNewsList";
	$(".content-wrapper").load(url);
}
// 数据中心按钮
function sjzxButton() {
	var url = "dataXinjianzhuzhaiController/findDataCentre";
	$(".content-wrapper").load(url);
}
// 抓取中心	
function zqzxButton() {
	alert("正在开发，尽请期待...");
}
// 审核中心
function shzxButton() {
	var url = "newsController/getAuditCentre";
	$(".content-wrapper").load(url);
}
// 日志中心
function rzzxButton() {
	var url = "LogController/findlogList";
	$(".content-wrapper").load(url);
}
// 统计中心
function tjzxButton() {
	var url = "statisticsController/countContre";
	$(".content-wrapper").load(url);
}
// 市研云盘
function syypButton() {
	alert("正在开发，尽请期待...");
}
