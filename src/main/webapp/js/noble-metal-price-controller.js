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

  function updateTransactionPrice(symbol, data) {
    var params = {
      symbol: symbol,
      sort: true,
      period: "all"
    };
    $http({
      method: "GET",
      url: "rest/stock-transactions.json",
      params: params
    }).then(function successCallback(response) {
      var counter = 0;
      var transactionData = response.data;
      for (counter = 0; counter < transactionData.length; counter++) {
        if (counter == 0) {
          data.buyPrice1 = transactionData[0].price;
          data.buyQuantity1 = transactionData[0].quantity;
        } else if (counter == 1) {
          data.buyPrice2 = transactionData[1].price;
          data.buyQuantity2 = transactionData[1].quantity;
        } else {
          break;
        }
      }
    }, function errorCallback(response) {
    });
  }
  
  $scope.nobleMetalPrices = [];      
  $scope.getData = function() {
    var counter;
    for (counter = 0; counter < symbols.length; counter++) {
      var symbol = symbols[counter];
      $http({
        method: "GET",
        url: "spring/rest/noble-metal-price",
        params: {symbol: symbol},
        symbol: symbol
      }).then(function successCallback(response) {
        var data = response.data;
        var symbol = response.config.symbol;
        data.strDatetime = (new Date(data.updateDatetime.epochSecond*1000)).toString();
        $scope.nobleMetalPrices.push(data);
        updateTransactionPrice(symbol, data);
      }, function errorCallback(response) {
      });
    }
    return;
  };
  $scope.getData();
});
