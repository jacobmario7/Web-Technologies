document.getElementById("registrationForm").addEventListener("submit", function (e) {
    e.preventDefault(); 

    let valid = true;

    
    const fullName = document.getElementById("fullName").value;
    const namePattern = /^[a-zA-Z\s]+$/; 
    if (fullName.length < 3) {
        document.getElementById("nameError").innerText = "Full name must be at least 3 characters long";
        document.getElementById("nameError").style.display = "block";
        valid = false;
    } else if (!namePattern.test(fullName)) {
        document.getElementById("nameError").innerText = "Name cannot contain numbers or special characters";
        document.getElementById("nameError").style.display = "block";
        valid = false;
    } else {
        document.getElementById("nameError").style.display = "none";
    }

    
    const email = document.getElementById("email").value;
    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (!emailPattern.test(email)) {
        document.getElementById("emailError").innerText = "Please enter a valid email";
        document.getElementById("emailError").style.display = "block";
        valid = false;
    } else {
        document.getElementById("emailError").style.display = "none";
    }

    
    const password = document.getElementById("password").value;
    if (password.length < 8) {
        document.getElementById("passwordError").innerText = "Password must be at least 8 characters long";
        document.getElementById("passwordError").style.display = "block";
        valid = false;
    } else {
        document.getElementById("passwordError").style.display = "none";
    }

    
    const confirmPassword = document.getElementById("confirmPassword").value;
    if (password !== confirmPassword) {
        document.getElementById("confirmPasswordError").innerText = "Passwords do not match";
        document.getElementById("confirmPasswordError").style.display = "block";
        valid = false;
    } else {
        document.getElementById("confirmPasswordError").style.display = "none";
    }

    if (valid) {
        alert("Form submitted successfully!");
        this.submit(); 
    }
});
