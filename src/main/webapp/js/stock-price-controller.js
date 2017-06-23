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
  $scope.getData = function() {
    var counter;
    for (counter = 0; counter < symbols.length; counter++) {
      var symbol = symbols[counter];
      $http({
        method: "GET",
        url: "spring/rest/stock-price",
        params: {symbol: symbol}
      }).then(function successCallback(response) {
        var data = response.data;
        data.strDatetime = (new Date(data.updateDatetime.epochSecond*1000)).toString();
        $scope.stockPrices.push(data);
      }, function errorCallback(response) {
      });
    }
    return;
  };
  $scope.getData();
});
