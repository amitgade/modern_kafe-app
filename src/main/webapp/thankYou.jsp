<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<body>
	<jsp:include page="/header.jsp" />
	<h2>Order your food:</h2>

	<p>Thank you - your order has been received. You need to pay $ ${total}</p>
	
	<!-- In case you eant to format using fmt taglib -->
	<%-- Thank you - your order has been received. You need to pay 
	<fmt:formatNumber value="${total}" type="currency" currencyCode="${currency}"/> --%>
	
	<p>The current status of your order is : ${status}</p>
	
	<jsp:include page="/footer.jsp" />

</body>
</html>