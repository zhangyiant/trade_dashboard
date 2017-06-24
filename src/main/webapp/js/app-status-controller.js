var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {

  function getAppStatus(appName, className) {
    $http({
      method: "GET",
      url: "spring/rest/trade-keep-alive/" + appName
    }).then(function successCallback(response) {
      var data = response.data;
      var isRunning = data.isRunning;
      if (isRunning) {
        $scope[className] = "btn btn-success";
      } else {
        $scope[className] = "btn btn-danger";
      }
    }, function errorCallback(response) {
    });
    return;
  }
    $scope.getStockData = function() {
        $http({
            method: "GET",
            url: "spring/rest/trade-keep-alive/stock_trade"
        }).then(function successCallback(response) {
            var data = response.data;
            var isRunning = data.isRunning;
            if (isRunning) {
                $scope.stockStatusClass = "btn btn-success";
            } else {
                $scope.stockStatusClass = "btn btn-danger";
            }
        }, function errorCallback(response) {
        });
        return;
    };
    $scope.getICBCData = function() {
        $http({
            method: "GET",
            url: "spring/rest/trade-keep-alive/icbc_trade"
        }).then(function successCallback(response) {
            var data = response.data;
            var isRunning = data.isRunning;
            if (isRunning) {
                $scope.icbcStatusClass = "btn btn-success";
            } else {
                $scope.icbcStatusClass = "btn btn-danger";
            }
        }, function errorCallback(response) {
        });
        return;
    };
    $scope.getStockData();
    $scope.getICBCData();
  getAppStatus("collect_stock_price", "collectStockPriceClass");
  getAppStatus("collect_noble_metal_price", "collectNobleMetalPriceClass");
});
