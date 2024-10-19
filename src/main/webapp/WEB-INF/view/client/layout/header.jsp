<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!-- Navbar start -->
<div class="container-fluid fixed-top">
  <div class="container topbar bg-primary d-none d-lg-block">
    <div class="d-flex justify-content-between">
      <div class="top-info ps-2">
        <small class="me-3"
          ><i class="fas fa-map-marker-alt me-2 text-secondary"></i>
          <a href="#" class="text-white">123 Street, New York</a></small
        >
        <small class="me-3"
          ><i class="fas fa-envelope me-2 text-secondary"></i
          ><a href="#" class="text-white">Email@Example.com</a></small
        >
      </div>
      <div class="top-link pe-2">
        <a href="#" class="text-white"
          ><small class="text-white mx-2">Privacy Policy</small>/</a
        >
        <a href="#" class="text-white"
          ><small class="text-white mx-2">Terms of Use</small>/</a
        >
        <a href="#" class="text-white"
          ><small class="text-white ms-2">Sales and Refunds</small></a
        >
      </div>
    </div>
  </div>
  <div class="container px-0">
    <nav class="navbar navbar-light bg-white navbar-expand-xl">
      <a href="/" class="navbar-brand"
        ><h1 class="text-primary display-6">Laptopshop</h1></a
      >
      <button
        class="navbar-toggler py-2 px-3"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarCollapse"
      >
        <span class="fa fa-bars text-primary"></span>
      </button>
      <div
        class="collapse navbar-collapse bg-white d-md-flex justify-content-between align-items-center"
        id="navbarCollapse"
      >
        <c:if test="${not empty pageContext.request.userPrincipal}">
          <div class="navbar-nav">
            <a href="/" class="nav-item nav-link active">Home</a>
            <a href="shop.html" class="nav-item nav-link">Product</a>
          </div>
          <div class="d-flex m-3 me-0">
            <a href="/cart-detail" class="position-relative me-4 my-auto">
              <i class="fa fa-shopping-bag fa-2x"></i>
              <span
                class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                style="top: -5px; left: 15px; height: 20px; min-width: 20px"
              >
                <c:out value="${sessionScope.sum}" />
              </span>
            </a>
            <div class="dropdown my-auto">
              <a
                href="#"
                class="dropdown"
                role="button"
                id="dropdownMenuLink"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i class="fas fa-user fa-2x"></i>
              </a>
              <ul
                class="dropdown-menu dropdown-menu-end p-4"
                arialabelledby="dropdownMenuLink"
              >
                <li
                  class="d-flex align-items-center flex-column"
                  style="min-width: 300px"
                >
                  <img
                    style="
                      width: 150px;
                      height: 150px;
                      border-radius: 50%;
                      overflow: hidden;
                    "
                    src="/images/product/${sessionScope.avatar}"
                  />
                  <div class="text-center my-3">
                    <c:out value="${sessionScope.fullName}" />
                  </div>
                </li>
                <li><a class="dropdown-item" href="/profile">Profile</a></li>
                <li><a class="dropdown-item" href="/history">History</a></li>
                <li>
                  <hr class="dropdown-divider" />
                </li>
                <li>
                  <form method="POST" action="/logout">
                    <input
                      type="hidden"
                      name="${_csrf.parameterName}"
                      value="${_csrf.token}"
                    />
                    <button class="dropdown-item">Logout</button>
                  </form>
                </li>
              </ul>
            </div>
          </div>
        </c:if>
        <c:if test="${empty pageContext.request.userPrincipal}">
          <a href="/login" class="d-block ms-auto">Login</a>
        </c:if>
      </div>
    </nav>
  </div>
</div>
<!-- Navbar End -->
