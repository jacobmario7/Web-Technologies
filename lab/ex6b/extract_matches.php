<?php
// Load the XML file
$xml_file = 'matches.xml';
if (!file_exists($xml_file)) {
    die("XML file not found!");
}

$xml = simplexml_load_file($xml_file);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Football Matches</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('football_bg.jpg');
            background-size: cover;
            color: white;
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        h1 {
            margin-bottom: 30px;
        }
        table {
            margin: 0 auto;
            width: 80%;
            border-collapse: collapse;
            background-color: rgba(0, 0, 0, 0.8);
        }
        th, td {
            border: 1px solid white;
            padding: 10px;
        }
        th {
            background-color: #333;
        }
    </style>
</head>
<body>
    <h1>Upcoming Football Matches</h1>
    <table>
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th>Time</th>
            <th>Location</th>
        </tr>
        <?php
        // Loop through each match in the XML and display in table rows
        foreach ($xml->match as $match) {
            echo "<tr>";
            echo "<td>" . $match->description . "</td>";
            echo "<td>" . $match->date . "</td>";
            echo "<td>" . $match->time . "</td>";
            echo "<td>" . $match->location . "</td>";
            echo "</tr>";
        }
        ?>
    </table>
</body>
</html>

