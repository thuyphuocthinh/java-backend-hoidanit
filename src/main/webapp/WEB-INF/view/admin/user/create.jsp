<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <!-- Latest compiled and minified CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
    <!-- Latest compiled JavaScript -->
  </head>
  <body>
    <jsp:include page="../layouts/header.jsp" />
    <div id="layoutSidenav">
      <!-- sidebar -->
      <jsp:include page="../layouts/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <div
          class="form-container"
          style="max-width: 500px; width: 90%; margin: 50px auto"
        >
          <h4>Create a user</h4>
          <hr />
          <form:form
            class="my-4"
            method="POST"
            action="/admin/user/post"
            modelAttribute="newUser"
            enctype="multipart/form-data"
          >
            <div class="row">
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="email" class="form-label fw-bold">Email</label>
                  <form:input
                    path="email"
                    type="email"
                    name="email"
                    id="email"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="password" class="form-label fw-bold"
                    >Password</label
                  >
                  <form:input
                    path="password"
                    type="password"
                    name="password"
                    id="password"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="phone" class="form-label fw-bold"
                    >Phone Number</label
                  >
                  <form:input
                    path="phone"
                    type="tel"
                    name="phone"
                    id="phone"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="fullName" class="form-label fw-bold"
                    >Full Name</label
                  >
                  <form:input
                    path="fullName"
                    type="fullName"
                    name="fullName"
                    id="fullName"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="address" class="form-label fw-bold"
                    >Address</label
                  >
                  <form:input
                    path="address"
                    type="text"
                    name="address"
                    id="address"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="address" class="form-label fw-bold">Role</label>
                  <form:select class="form-select" path="role.name">
                    <option selected disabled>-- Select role --</option>
                    <form:option value="ADMIN">Admin</form:option>
                    <form:option value="USER">User</form:option>
                  </form:select>
                </div>
              </div>
              <div class="col-12">
                <div class="form-group mb-3">
                  <label class="form-label fw-bold" for="avatarFile"
                    >Avatar</label
                  >
                  <input
                    type="file"
                    name="avatarFile"
                    class="form-control"
                    id="avatarFile"
                    accept=".png, .jpg, .jpeg"
                  />
                </div>
              </div>
              <div class="col-12 mb-3">
                <img id="avatarPreview" style="width: 100%" />
              </div>
              <div class="form-group">
                <button class="btn btn-primary" type="submit">Create</button>
              </div>
            </div>
          </form:form>
        </div>
        <jsp:include page="../layouts/footer.jsp" />
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
    <script>
      $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        // get file from input change event
        avatarFile.change(function (e) {
          // create a url link (blob of the image)
          // blob:http://localhost:8080/b6284246-e462-4c7b-bab1-76a88c8a68a7
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#avatarPreview").attr("src", imgURL);
          $("#avatarPreview").css({ display: "block" });
        });
      });
    </script>
  </body>
</html>
