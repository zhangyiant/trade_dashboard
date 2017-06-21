var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {

  var symbols = ["人民币账户白银",
                 "人民币账户黄金",
                 "人民币账户铂金",
                 "人民币账户钯金",
                 "美元账户白银",
                 "美元账户黄金",
                 "美元账户铂金",
                 "美元账户钯金"];

  $scope.nobleMetalPrices = [];      
  $scope.getData = function() {
    var counter;
    for (counter = 0; counter < symbols.length; counter++) {
      var symbol = symbols[counter];
      $http({
        method: "GET",
        url: "spring/rest/noble-metal-price",
        params: {symbol: symbol}
      }).then(function successCallback(response) {
        var data = response.data;
        data.strDatetime = (new Date(data.updateDatetime.epochSecond*1000)).toString();
        $scope.nobleMetalPrices.push(data);
      }, function errorCallback(response) {
      });
    }
    return;
  };
  $scope.getData();
});
