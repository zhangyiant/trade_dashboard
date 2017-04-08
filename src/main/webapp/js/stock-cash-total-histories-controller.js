var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {
    function getPercentageData() {
        var data = new Array();
        var histories = $scope.stockCashTotalHistories;
        var historiesLength = histories.length;
        var baseValue = histories[historiesLength - 1].totalValue;
        for (counter = 0;
             counter < historiesLength;
             counter++) {
            var point = new Array();
            var d = histories[counter].epochMilli;
            var v = (histories[counter].totalValue - baseValue) / baseValue * 100;
            point.push(d);
            point.push(v);
            data.push(point);
        }
        return data;
    }
    function getAbsoluteValueData() {
        var data = new Array();
        for (counter = 0;
             counter < $scope.stockCashTotalHistories.length;
             counter++) {
            var point = new Array();
            var d = $scope.stockCashTotalHistories[counter].epochMilli;
            var v = $scope.stockCashTotalHistories[counter].totalValue;
            point.push(d);
            point.push(v);
            data.push(point);
        }
        return data;
    }
    function updateChart () {
        var data;
        var counter;
        if ($scope.percent === "percent") {
            data = getPercentageData();
        } else {
            data = getAbsoluteValueData();
        }
        data.reverse();
        var chart = Highcharts.chart("trends", {
            title: {
                text: "Stock Cash History Trends"
            },
            yAxix: {
                title: {
                    text: "Total Value"
                }
            },
            xAxis: {
                type: "datetime"
            },
            series: [{
                name: "value trends",
                data: data
                }]
        });
        return;
    }
    
    $scope.getData = function() {
        $http({
            method: "GET",
            url: "rest/stock-cash-total-histories.json",
            params: {symbol: $scope.symbol,
                     period: $scope.period}
        }).then(function successCallback(response) {
            $scope.stockCashTotalHistories = response.data;
            updateChart();
        }, function errorCallback(response) {
        });
        return;
    };
    function updateStockInfos() {
        $http({
            method: "GET",
            url: "rest/stock-infos.json"
        }).then(function successCallback(response) {
            $scope.stockInfos = response.data;
            if ($scope.stockInfos.length > 0) {
                $scope.symbol = $scope.stockInfos[0].symbol;
            }
        }, function errorCallback(responsse) {
        });
        return;
    }
    updateStockInfos();
    $scope.percent="value";
    $scope.period="all";
});
app.filter("epochMilliToDatetimeString", function () {
    return function(epochMilli) {
        if (!epochMilli) {
            return "";
        } else {
            var d = new Date(epochMilli);
            return d.toString();
        }
    };
});
