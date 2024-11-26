<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('football_bg.jpg');
            background-size: cover;
            color: white;
            text-align: center;
            padding: 50px;
        }
        form {
            background-color: rgba(0, 0, 0, 0.8);
            padding: 20px;
            display: inline-block;
            border-radius: 10px;
        }
        label, input, button {
            display: block;
            margin: 10px 0;
        }
        input {
            padding: 10px;
            width: 90%;
            border-radius: 5px;
            border: none;
        }
        button {
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>
    <h2>Football Match Manager Login</h2>
    <form onsubmit="return validateLogin();">
        <label for="username">Username:</label>
        <input type="text" id="username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" required>
        <button type="submit">Login</button>
    </form>

    <script>
        function validateLogin() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            if (username === "manager" && password === "football123") {
                window.location.href = "match_manager.php";
            } else {
                alert("Invalid credentials!");
            }
            return false;
        }
    </script>
</body>
</html>
