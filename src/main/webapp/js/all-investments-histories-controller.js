var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {
    function updateChart () {
        var data = new Array();
        for (var counter = 0;
             counter < $scope.allInvestmentsHistories.length;
             counter++) {
            var point = new Array();
            var d = $scope.allInvestmentsHistories[counter].epochMilli;
            var v = $scope.allInvestmentsHistories[counter].totalValue;
            point.push(d);
            point.push(v);
            data.push(point);
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
    $scope.period = "all";
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
