<html>
<body>
	<jsp:include page="/header.jsp" />
	<h2>Order your food:</h2>

	<% 
	Double total = (Double)request.getAttribute("total");
	out.println("Thank you - your order has been received. You need to pay $" + total);
	%>
	
	<jsp:include page="/footer.jsp" />

</body>
</html>