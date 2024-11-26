<?php
$servername = "localhost";
$username = "root";
$password = "";
$db = "football_schedule";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $db);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Handle match deletion
if (isset($_GET['delete_id'])) {
    $match_id = $_GET['delete_id'];

    $delete_sql = "DELETE FROM matches WHERE id = ?";
    if ($stmt = $conn->prepare($delete_sql)) {
        $stmt->bind_param("i", $match_id);
        if ($stmt->execute()) {
            echo "<p>Match deleted successfully.</p>";
        } else {
            echo "<p>Error deleting match: " . $stmt->error . "</p>";
        }
        $stmt->close();
    }
}

// Handle match addition
if (isset($_POST['add_match'])) {
    $match_description = $_POST['match_description'];
    $match_date = $_POST['match_date'];

    $insert_sql = "INSERT INTO matches (match_description, match_date) VALUES (?, ?)";
    if ($stmt = $conn->prepare($insert_sql)) {
        $stmt->bind_param("ss", $match_description, $match_date);
        if ($stmt->execute()) {
            echo "<p>Match added successfully!</p>";
        } else {
            echo "<p>Error adding match: " . $stmt->error . "</p>";
        }
        $stmt->close();
    }
}

// Retrieve all matches
$sql = "SELECT * FROM matches";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Football Match Manager</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: url('football_bg.jpg');
            background-size: cover;
            background-attachment: fixed;
            color: white;
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        form {
            background-color: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 8px;
            display: inline-block;
            margin-bottom: 30px;
        }
        input, button {
            padding: 10px;
            margin: 10px 0;
            width: calc(100% - 24px);
            border-radius: 5px;
            border: none;
        }
        input {
            background-color: #444;
            color: white;
        }
        button {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #104e8b;
        }
        table {
            margin: 20px auto;
            width: 90%;
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
        a {
            color: #ff4500;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Manage Football Matches</h1>

    <!-- Match Addition Form -->
    <form action="" method="POST">
        <label for="match_description">Match Description:</label><br>
        <input type="text" id="match_description" name="match_description" required><br>
        <label for="match_date">Match Date:</label><br>
        <input type="date" id="match_date" name="match_date" required><br>
        <button type="submit" name="add_match">Add Match</button>
    </form>

    <!-- Match List -->
    <h2>Scheduled Matches</h2>
    <?php
    if ($result->num_rows > 0) {
        echo "<table>";
        echo "<tr><th>ID</th><th>Description</th><th>Date</th><th>Action</th></tr>";
        while ($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td>" . $row["id"] . "</td>";
            echo "<td>" . $row["match_description"] . "</td>";
            echo "<td>" . $row["match_date"] . "</td>";
            echo "<td><a href='?delete_id=" . $row["id"] . "'>Delete</a></td>";
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "<p>No matches scheduled yet. Add one now!</p>";
    }

    mysqli_close($conn);
    ?>
</body>
</html>
