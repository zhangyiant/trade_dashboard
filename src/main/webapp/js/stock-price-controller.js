var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {

  var symbols = ["000568",
                 "002367",
                 "600019",
                 "600115",
                 "600584",
                 "601111",
                 "601390",
                 "601398",
                 "601857",
                 "601998"];

  $scope.stockPrices = [];

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

  $scope.getData = function() {
    var counter;
    for (counter = 0; counter < symbols.length; counter++) {
      var symbol = symbols[counter];
      $http({
        method: "GET",
        url: "spring/rest/stock-price",
        params: {symbol: symbol},
        symbol: symbol
      }).then(function successCallback(response) {
        var data = response.data;
        var symbol = response.config.symbol;
        data.strDatetime = (new Date(data.updateDatetime.epochSecond*1000)).toString();
        $scope.stockPrices.push(data);
        updateTransactionPrice(symbol, data);
      }, function errorCallback(response) {
      });
    }
    return;
  };
  $scope.getData();
});
app.filter("rowClass", function () {
  return function(stockPrice) {
    if ("buyPrice1" in stockPrice) {
      if (stockPrice.price < stockPrice.buyPrice1) {
        return "success";
      } else {
        if ("buyPrice2" in stockPrice) {
          if (stockPrice.price < stockPrice.buyPrice2) {
            return "warning";
          } else {
            return "danger";
          }
        } else {
          return "warning";
        }
      }
    }
    return "";
  };
});
