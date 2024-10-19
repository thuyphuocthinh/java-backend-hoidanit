<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
    <title>Document</title>
  </head>
  <body class="sb-nav-fixed">
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
            <h4>Table Products</h4>
            <a class="btn btn-primary" href="/admin/product/create"
              >+ Add new product</a
            >
          </div>
          <div class="home-body mt-4">
            <table
              class="table table-striped table-hover"
              style="vertical-align: middle; text-align: center"
            >
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Quantity</th>
                  <th>Factory</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="product" items="${listProducts}">
                  <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.factory}</td>
                    <td>
                      <a
                        href="/admin/product/view/${product.id}"
                        class="btn btn-success"
                        >View</a
                      >
                      <a
                        href="/admin/product/edit/${product.id}"
                        class="btn btn-warning"
                        >Edit</a
                      >
                      <a
                        href="/admin/product/delete/${product.id}"
                        class="btn btn-danger"
                        >Delete</a
                      >
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <nav aria-label="Page navigation">
            <ul class="pagination">
              <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                <a
                  class="page-link"
                  href="/admin/product?page=${currentPage-1}"
                  aria-label="Previous"
                >
                  <span aria-hidden="true">&laquo;</span>
                  <span class="sr-only">Previous</span>
                </a>
              </li>
              <c:forEach begin="1" end="${totalPage}" varStatus="loop">
                <li
                  class="page-item ${currentPage == loop.index ? 'active' : ''}"
                >
                  <a class="page-link" href="/admin/product?page=${loop.index}">
                    ${loop.index}
                  </a>
                </li>
              </c:forEach>
              <li class="page-item">
                <a
                  class="page-link ${currentPage == totalPage ? 'disabled' : ''}"
                  href="/admin/product?page=${currentPage+1}"
                  aria-label="Next"
                >
                  <span aria-hidden="true">&raquo;</span>
                  <span class="sr-only">Next</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
        <jsp:include page="../layouts/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
  </body>
</html>
