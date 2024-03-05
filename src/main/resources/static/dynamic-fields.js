$(document).ready(function() {
    var towerIndex = 1;

    $("#addTower").click(function() {
        var towerField = '<div class="towerField">' +
            '<label for="towerNumber">Tower Number:</label>' +
            '<input type="text" id="towerNumber" name="towers[' + towerIndex + '].towerNumber" required><br><br>' +
            '<label for="towerName">Tower Name:</label>' +
            '<input type="text" id="towerName" name="towers[' + towerIndex + '].towerName" required><br><br>' +
            '<label for="latitude">Latitude:</label>' +
            '<input type="text" id="latitude" name="towers[' + towerIndex + '].latitude" required><br><br>' +
            '<label for="longitude">Longitude:</label>' +
            '<input type="text" id="longitude" name="towers[' + towerIndex + '].longitude" required><br><br>' +
            '</div>';

        $("#towerFields").append(towerField);
        towerIndex++;
    });
});