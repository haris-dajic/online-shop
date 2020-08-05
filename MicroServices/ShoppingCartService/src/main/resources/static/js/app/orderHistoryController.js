app.controller('orderHistoryController', [
    '$scope', '$location','$http',
    function ($scope, $location, $http) {

    //kao i u market info samo ocitati iz baze u model narudzbe
        $scope.model = {

            narudzbe: [
                {
                    broj: "1234321121",
                    brojProizvoda: 2,
                    datum: "29.05.2019",
                    vrijeme: "13:10",
                    ukupnaCijena: "24,99 KM",
                    proizvodi: [
                        {
                            naziv: "Stolica",
                            opis: "Stolica od hrasta.",
                            cijena: "12,99 KM",
                            kolicina: 1
                        }, {
                            naziv: "Stol",
                            opis: "Stol od hrasta.",
                            cijena: "12,00 KM",
                            kolicina: 1
                        }
                    ]
                }, {
                    broj: "1234321120",
                    brojProizvoda: 1,
                    datum: "26.05.2019",
                    vrijeme: "11:10",
                    ukupnaCijena: "29,99 KM",
                    proizvodi: [
                        {
                            naziv: "Šator",
                            opis: "Maskirni, 4x3.5m.",
                            cijena: "29,99 KM",
                            kolicina: 1
                        }
                    ]
                }, {
                    broj: "1234321119",
                    brojProizvoda: 3,
                    datum: "23.05.2019",
                    vrijeme: "11:10",
                    ukupnaCijena: "540,99 KM",
                    proizvodi: [
                        {
                            naziv: "Krevet",
                            opis: "Štif, crna boja, 2x2.2m.",
                            cijena: "440,99 KM",
                            kolicina: 1
                        }, {
                            naziv: "Posteljina",
                            opis: "Pernato punjenje, 2x2m.",
                            cijena: "90,00 KM",
                            kolicina: 1
                        }, {
                            naziv: "Jastuk",
                            opis: "Pernato punjenje, 70x40cm.",
                            cijena: "10,00 KM",
                            kolicina: 1
                        }
                    ]
                }, {
                    broj: "1234321118",
                    brojProizvoda: 4,
                    datum: "21.05.2019",
                    vrijeme: "11:10",
                    ukupnaCijena: "99,99 KM",
                    proizvodi: [
                        {
                            naziv: "Lonac",
                            opis: "Inkos, 2dm^3.",
                            cijena: "64,99 KM",
                            kolicina: 1
                        }, {
                            naziv: "Tava",
                            opis: "Inoks, 30x30cm.",
                            cijena: "30,00 KM",
                            kolicina: 1
                        }, {
                            naziv: "Kašika",
                            opis: "Inoks.",
                            cijena: "5,00 KM",
                            kolicina: 2
                        }
                    ]
                }
            ],
            detalji: {
                brojNarudzbe: null,
                proizvodi: null,
                ukupnaCijena: null
            }
        };

        $scope.prikaziDetalje = prikaziDetalje;
        $scope.prikaziDetaljeInit = prikaziDetaljeInit;

        prikaziDetaljeInit();

        function prikaziDetaljeInit() {
            var orderScrollBar = document.getElementById("orderScrollBar");
            var detailsScrollBar = document.getElementById("detailsScrollBar");

            var urlUsername = $location.absUrl() + "username";
            $scope.model.username = null;
            $http.get(urlUsername).then(function (response) {
                $scope.model.username = response.data;
            }, function error(reason) {
                console.log("Neuspjesan prijem username-a: " + response.statusText);
            });

            console.log($scope.model.narudzbe.length);

            switch ($scope.model.narudzbe.length) {
                case 1:
                    orderScrollBar
                        .classList
                        .add("orderScrollBarSolo");
                    break;
                case 2:
                    orderScrollBar
                        .classList
                        .add("orderScrollBarPair");
                    break;
                default:
                    orderScrollBar
                        .classList
                        .add("orderScrollBar");
            }

            $scope.model.detalji.brojNarudzbe = $scope
                .model
                .narudzbe[0]
                .broj;
            $scope.model.detalji.proizvodi = $scope
                .model
                .narudzbe[0]
                .proizvodi;
            $scope.model.detalji.ukupnaCijena = $scope
                .model
                .narudzbe[0]
                .ukupnaCijena;

            switch ($scope.model.detalji.proizvodi.length) {
                case 1:
                    detailsScrollBar
                        .classList
                        .remove("detailsScrollBar");
                    break;
                default:
                    detailsScrollBar
                        .classList
                        .add("detailsScrollBar");
            }

            console.log($scope.model.detalji.proizvodi.length);
        }

        function prikaziDetalje(brojNaruzbe) {

            var narudzba = null;
            var detailsScrollBar = document.getElementById("detailsScrollBar");
            1
            for (var i = 0; i < $scope.model.narudzbe.length; i++)
                if ($scope.model.narudzbe[i].broj === brojNaruzbe) {
                    narudzba = $scope
                        .model
                        .narudzbe[i];
                    break;
                }

            $scope.model.detalji.brojNarudzbe = narudzba.broj;
            $scope.model.detalji.proizvodi = narudzba.proizvodi;
            $scope.model.detalji.ukupnaCijena = narudzba.ukupnaCijena;

            switch ($scope.model.detalji.proizvodi.length) {
                case 1:
                    detailsScrollBar
                        .classList
                        .remove("detailsScrollBar");
                    break;
                default:
                    detailsScrollBar
                        .classList
                        .add("detailsScrollBar");
            }
        }

    }
]);