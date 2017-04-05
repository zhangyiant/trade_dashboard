var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {
    $http({
        method: "GET",
        url: "rest/all-investments-histories.json"
    }).then(function successCallback(response) {
        $scope.allInvestmentsHistories = response.data;
    }, function errorCallback(response) {
    });
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
