var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {

    $scope.getData = function() {
        $http({
            method: "GET",
            url: "spring/rest/stock-cashes.json"
        }).then(function successCallback(response) {
            $scope.stockCashes = response.data;
        }, function errorCallback(response) {
        });
        return;
    };
    $scope.getData();
});
