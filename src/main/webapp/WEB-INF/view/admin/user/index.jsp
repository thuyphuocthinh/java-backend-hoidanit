<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

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
          class="home"
          style="max-width: 1200px; margin: 50px auto; width: 95%"
        >
          <div
            class="home-top d-flex align-items-center justify-content-between"
          >
            <h4>Table User</h4>
            <a class="btn btn-primary" href="/admin/user/create">+ Add new user</a>
          </div>
          <div class="home-body mt-4">
            <table
              class="table table-striped table-hover"
              style="vertical-align: middle; text-align: center"
            >
              <thead>
                <tr>
                  <th>ID</th>
                  <th>FullName</th>
                  <th>Email</th>
                  <th>Phone</th>
                  <th>Addess</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="user" items="${listUsers}">
                  <tr>
                    <td>${user.id}</td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.address}</td>
                    <td>
                      <a href="/user/view/${user.id}" class="btn btn-success"
                        >View</a
                      >
                      <a href="/user/edit/${user.id}" class="btn btn-warning"
                        >Edit</a
                      >
                      <a href="/user/delete/${user.id}" class="btn btn-danger"
                        >Delete</a
                      >
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
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
  </body>
</html>
