<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
    <link rel="stylesheet" href="/css/demo.css" />
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  </head>
  <body>
    <div
      class="form-container"
      style="max-width: 500px; width: 90%; margin: 50px auto"
    >
      <h4>Create a user</h4>
      <hr />
      <form:form class="my-4" method="POST" action="/admin/user/post" modelAttribute="newUser">
        <div class="form-group mb-3">
          <label for="email" class="form-label fw-bold">Email</label>
          <form:input path="email" type="email" name="email" id="email" class="form-control" />
        </div>
        <div class="form-group mb-3">
          <label for="password" class="form-label fw-bold">Password</label>
          <form:input path="password" type="password" name="password" id="password" class="form-control" />
        </div>
        <div class="form-group mb-3">
          <label for="phone" class="form-label fw-bold">Phone Number</label>
          <form:input path="phone" type="tel" name="phone" id="phone" class="form-control" />
        </div>
        <div class="form-group mb-3">
          <label for="fullName" class="form-label fw-bold">Full Name</label>
          <form:input path="fullName" type="fullName" name="fullName" id="fullName" class="form-control" />
        </div>
        <div class="form-group mb-3">
          <label for="address" class="form-label fw-bold">Address</label>
          <form:input path="address" type="text" name="address" id="address" class="form-control" />
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Create</button>
        </div>
      </form:form>
    </div>
  </body>
</html>
