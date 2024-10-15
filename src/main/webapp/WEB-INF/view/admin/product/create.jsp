<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
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
          <h4>Create a product</h4>
          <hr />
          <form:form
            class="my-4"
            method="POST"
            action="/admin/product/post"
            modelAttribute="newProduct"
            enctype="multipart/form-data"
          >
            <div class="row">
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="name" class="form-label fw-bold">Name</label>
                  <form:input
                    path="name"
                    type="name"
                    name="name"
                    id="name"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="price" class="form-label fw-bold">Price</label>
                  <form:input
                    path="price"
                    type="number"
                    min="0"
                    name="price"
                    id="price"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-12">
                <div class="form-group mb-3">
                  <label for="longDescription" class="form-label fw-bold"
                    >Detail Description</label
                  >
                  <textarea
                    class="form-control"
                    name="longDescription"
                    id="longDescription"
                    path="longDescription"
                    rows="3"
                  ></textarea>
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="shortDescription" class="form-label fw-bold"
                    >Short Description</label
                  >
                  <form:input
                    path="shortDescription"
                    type="text"
                    name="shortDescription"
                    id="shortDescription"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="quantity" class="form-label fw-bold"
                    >Quantity</label
                  >
                  <form:input
                    path="quantity"
                    min="0"
                    type="number"
                    name="quantity"
                    id="quantity"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="factory" class="form-label fw-bold"
                    >Factory</label
                  >
                  <form:select id="factory" class="form-select" path="factory">
                    <option selected disabled>-- Select Factory --</option>
                    <form:option value="Apple">Apple</form:option>
                    <form:option value="Dell">Dell</form:option>
                    <form:option value="Asus">Asus</form:option>
                    <form:option value="Acer">Acer</form:option>
                  </form:select>
                </div>
              </div>
              <div class="col-md-6 col-12">
                <div class="form-group mb-3">
                  <label for="target" class="form-label fw-bold">Target</label>
                  <form:select id="target" class="form-select" path="target">
                    <option selected disabled>-- Select Target --</option>
                    <form:option value="Gaming">Gaming</form:option>
                    <form:option value="Office">Office</form:option>
                    <form:option value="Coding">Coding</form:option>
                  </form:select>
                </div>
              </div>
              <div class="col-12">
                <div class="form-group mb-3">
                  <label class="form-label fw-bold" for="image">Image</label>
                  <input
                    type="file"
                    name="image"
                    class="form-control"
                    id="image"
                    accept=".png, .jpg, .jpeg"
                  />
                </div>
              </div>
              <div class="col-12 mb-3">
                <img id="imgPreview" style="width: 100%" />
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
        const avatarFile = $("#image");
        // get file from input change event
        avatarFile.change(function (e) {
          // create a url link (blob of the image)
          // blob:http://localhost:8080/b6284246-e462-4c7b-bab1-76a88c8a68a7
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#imgPreview").attr("src", imgURL);
          $("#imgPreview").css({ display: "block" });
        });
      });
    </script>
  </body>
</html>
