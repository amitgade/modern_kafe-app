<%@ page import="com.smartcode.data.MenuDao"%>
<%@ page import="com.smartcode.data.MenuDaoFactory"%>
<%@ page import="com.smartcode.domain.MenuItem"%>
<%@ page import="java.util.List"%>


<%
	MenuDao menuDao = MenuDaoFactory.getMenuDao();
	List<MenuItem> menuItems = menuDao.getFullMenu();
%>

<html>
<body>
	<h1>Modern Kafe</h1>
	<h2>Menu</h2>
	<ul>
		<%
		for (MenuItem menuItem : menuItems) {
		%>
			<li> <%= menuItem %> </li>
		<%
		}
		%>
	</ul>

	<a href='searchResults.html?searchTerm=vegetable'>View all of our vegetable dishes</a>
	<a href='order.html'>Place an order for food</a>

</body>
</html>