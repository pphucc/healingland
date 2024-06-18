<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.HomestayDAO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Beautiful Homestay - Homestay Profile</title>
        <!-- META TAGS -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- FAV ICON(BROWSER TAB ICON) -->
        <link rel="shortcut icon" href="images/fav.ico" type="image/x-icon">
        <!-- GOOGLE FONT -->
        <link href="https://fonts.googleapis.com/css?family=Poppins%7CQuicksand:500,700" rel="stylesheet">
        <!-- BOOTSTRAP CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <!-- CUSTOM CSS -->
        <link href="${pageContext.request.contextPath}/css/homestay/view/homestay_view.css" rel="stylesheet" type="text/css" />
    </head>

    <body data-ng-app="">
        <%
    String homestayIdParam = request.getParameter("homestay_id");
    Integer homestayId = null;
    if (homestayIdParam != null) {
        try {
            homestayId = Integer.parseInt(homestayIdParam);
        } catch (NumberFormatException e) {
            // Handle the error, e.g., log it or set homestayId to a default value
            homestayId = null; // Or set to a default valid ID
        }
    }
    Object homestay = (homestayId != null) ? HomestayDAO.getHomestayById(homestayId) : null;
    request.setAttribute("homestay", homestay);
        %>
        <c:set var="homestay" value="${homestay}" />
        <!-- Navigation Section -->
        <div class="nav-section">
            <div class="nav-container">
                <a href="#gallery">Gallery</a>
                <a href="#information">Information</a>
                <a href="#amenities">Amenities</a>
                <a href="#locations">Nearby</a>
                <a href="#rules">Rules</a>
                <a href="#rooms">Rooms</a>
                <a href="./feedback.jsp?homestay_id=<c:out value="${homestay.ht_id}"></c:out>">User Reviews</a>
                </div>
            </div>
            <h1 class="text-success">PAYMENT SUCCESSFULLY.</h1>

        </div>
        <!-- BOOTSTRAP JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', (event) => {
                const today = new Date().toISOString().split('T')[0];
                document.getElementById('checkinDate').setAttribute('min', today);
                document.getElementById('checkoutDate').setAttribute('min', today);

                document.getElementById('checkinDate').addEventListener('change', (event) => {
                    const checkinDate = event.target.value;
                    const minCheckoutDate = new Date(new Date(checkinDate).getTime() + (24 * 60 * 60 * 1000)).toISOString().split('T')[0];
                    document.getElementById('checkoutDate').setAttribute('min', minCheckoutDate);
                    calculateNights();
                });

                document.getElementById('checkoutDate').addEventListener('change', calculateNights);
                // Scroll to rooms if needed
                const scrollToRooms = ${scrollToRooms != null ? scrollToRooms : 'false'};
                if (scrollToRooms) {
                    document.getElementById('rooms').scrollIntoView();
                }
            });

            function calculateNights() {
                const checkinDate = new Date(document.getElementById('checkinDate').value);
                const checkoutDate = new Date(document.getElementById('checkoutDate').value);
                const nightCountElement = document.getElementById('nightCount').querySelector('p');

                if (checkinDate && checkoutDate && checkoutDate > checkinDate) {
                    const differenceInTime = checkoutDate.getTime() - checkinDate.getTime();
                    const differenceInDays = differenceInTime / (1000 * 3600 * 24);
                    nightCountElement.textContent = `${differenceInDays} NIGHTS`;
                    document.getElementById('totalNights').textContent = differenceInDays;
                    updatePricingSummary();
                } else {
                    nightCountElement.textContent = 'NIGHTS';
                    document.getElementById('totalNights').textContent = 0;
                    updatePricingSummary();
                }
            }
            // Initialize the summary with default values
            calculateNights();
    </script>
</body>

</html>
