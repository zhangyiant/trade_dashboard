var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {

    $scope.getData = function() {
        $http({
            method: "GET",
            url: "rest/stock-closed-transactions.json",
            params: {period: $scope.period}
        }).then(function successCallback(response) {
            $scope.stockClosedTransactions = response.data;
        }, function errorCallback(response) {
        });
        return;
    };
    $scope.period="1month";
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
