var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {

    $scope.getData = function() {
        var params = {};
        params.period = $scope.period;
        if ($scope.symbol !== "all") {
            params.symbol = $scope.symbol;
        }
      params.sort = $scope.sort;
        $http({
            method: "GET",
            url: "rest/stock-transactions.json",
            params: params
        }).then(function successCallback(response) {
            $scope.stockTransactions = response.data;
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
            var all_symbol = {
                name: "All stocks",
                symbol: "all"
            };
            $scope.stockInfos.push(all_symbol);
            $scope.symbol = "all";
            $scope.getData();
        }, function errorCallback(responsse) {
        });
        return;
    }
  $scope.period="1month";
  $scope.sort = false;
    updateStockInfos();
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
