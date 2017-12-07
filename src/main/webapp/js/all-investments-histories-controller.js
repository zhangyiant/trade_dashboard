var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {
    function getPercentageData() {
        var data = new Array();
        var histories = $scope.allInvestmentsHistories;;
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
        var histories = $scope.allInvestmentsHistories;
        for (counter = 0;
             counter < histories.length;
             counter++) {
            var point = new Array();
            var d = histories[counter].epochMilli;
            var v = histories[counter].totalValue;
            point.push(d);
            point.push(v);
            data.push(point);
        }
        return data;
    }
    function updateChart () {
        var data;
        if ($scope.percent === "percent") {
            data = getPercentageData();
        } else {
            data = getAbsoluteValueData();
        }
        data.reverse();
        var chart = Highcharts.chart("trends", {
            title: {
                text: "History Investment Trends"
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
            url: "rest/all-investments-histories.json",
            params: {period: $scope.period}
        }).then(function successCallback(response) {
            $scope.allInvestmentsHistories = response.data;
            updateChart();
        }, function errorCallback(response) {
        });
        return;
    };
    $scope.period = "1month";
    $scope.percent = "percent";
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
