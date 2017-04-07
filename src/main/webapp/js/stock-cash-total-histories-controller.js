var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {
    function updateChart () {
        var data = new Array();
        for (var counter = 0;
             counter < $scope.stockCashTotalHistories.length;
             counter++) {
            var point = new Array();
            var d = $scope.stockCashTotalHistories[counter].epochMilli;
            var v = $scope.stockCashTotalHistories[counter].totalValue;
            point.push(d);
            point.push(v);
            data.push(point);
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
            params: {symbol: $scope.symbol}
        }).then(function successCallback(response) {
            $scope.stockCashTotalHistories = response.data;
            updateChart();
        }, function errorCallback(response) {
        });
        return;
    };
    $scope.symbol = "人民币账户白银";
    $scope.getData();
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
