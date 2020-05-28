<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<script type="text/javascript">
	function updateStatus() {
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if (this.readyState == 4) {
				document.getElementById("status").innerHTML = this.responseText;
			}
		}
		request.open("GET", "/updatedStatus?id=${id}", true)
		request.send();
	}
	
	<%-- function that update status every 2 seconds --%>
	window.setInterval(function() {
		updateStatus();
	}, 2000)

	</script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<h2>Order your food:</h2>

	<p>Thank you - your order has been received. You need to pay $
		${total}</p>

	<!-- In case you eant to format using fmt taglib -->
	<%-- Thank you - your order has been received. You need to pay 
	<fmt:formatNumber value="${total}" type="currency" currencyCode="${currency}"/> --%>

	<p>
		The current status of your order is : <span id="status">${status}</span>
		<input type="button" value="refresh status" onclick="updateStatus()" />
	</p>

	<jsp:include page="/footer.jsp" />

</body>
</html>