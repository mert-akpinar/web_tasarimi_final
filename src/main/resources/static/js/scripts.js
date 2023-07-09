$(document).ready(function () {
    $("#registerForm").submit(function (event) {
        event.preventDefault();
        if (validateRegisterForm()) {
            let name, surname, email, password;
            name = $("#name").val()
            email = $("#email").val()
            password = $("#password").val()
            ajax_register_post(name, surname, email, password)
        }
    })
})
$(document).ready(function () {
    $("#loginForm").submit(function (event) {
        event.preventDefault()
        if (validateLoginForm()) {
            let email = $("#email").val()
            let password = $("#password").val()
            ajax_login_post(email, password);
        }
    })
})
let ajax_login_post = (emailVal, passwordVal) => {
    const data = {
        email: emailVal,
        password: passwordVal
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/validate-login",
        data: JSON.stringify(data),
        dataType: "json",
        cache: false,
        timeout: 10000,
        success: function (data) {
            if (data.status) {
                customPopoutAlert("Login Success", "success")
            } else {
                customPopoutAlert("Bad Credentials", "error")
            }
        },
        error: function (text) {
            customPopoutAlert("Alert" + text.status + "Check error code and try again", "error")
        }
    })
}
let ajax_register_post = (nameVal, emailVal, passwordVal) => {
    const UserDto = {
        firstName: nameVal,
        email: emailVal,
        password: passwordVal
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/create-user",
        data: JSON.stringify(UserDto),
        dataType: "json",
        cache: false,
        timeout: 10000,
        success: function (data) {
            if (data.status) {
                customPopoutAlert("Registration Success," +
                    " Redirecting to the login page", "success")
            } else {
                customPopoutAlert("Email already used by another user. try different email")
            }
        },
        error: function (xhr) {
            customPopoutAlert("Error:" + xhr.status + "Check the error code and try again", "error")
        }
    });
}
let validateRegisterForm = () => {
    const $form = $("#registerForm");
    if ($form.length) {
        $form.validate({
            errorClass: "error",
            errorElement: "div",
            errorPlacement: function (error, element) {
                error.insertAfter(element);
            },
            highlight: function (element) {
                 $(element).closest(".form-control").addClass("is-invalid");
            },
            unhighlight: function (element) {
                $(element).closest(".form-control").removeClass("is-invalid");
            },
            rules: {
        D        name: {
                    required: true
                },
                surname: {
                    required: true
                },
                email: {
                    required: true,
                },
                password: {
                    required: true,
                },
                password2: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                name: {
                    required: "Name is required"
                },
                surname: {
                    required: "Surname is required"
                },
                email: {
                    required: "Email is required",
                },
                password: {
                    required: "Enter your password"
                },
                password2: {
                    required: "Confirm your password",
                    equalTo: "Passwords doesn't match"
                }
            },
        });
        return $form.valid();
    }
    return false;
}
