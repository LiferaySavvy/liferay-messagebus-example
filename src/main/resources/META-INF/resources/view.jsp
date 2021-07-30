
<%@ include file="/init.jsp" %>
<h2>Welcome to Liferay Message Bus</h2><br/>
<portlet:renderURL var="renderSendMessage">
	<portlet:param name="mvcRenderCommandName" value="/view/send_message" />
</portlet:renderURL>
<portlet:renderURL var="renderViewStatistics">
	<portlet:param name="mvcRenderCommandName" value="/view/destination_statistics" />
</portlet:renderURL>
<aui:button href="<%= renderSendMessage %>" value="Send Message" />
<aui:button href="<%= renderViewStatistics %>" value="Destination Statistics" />
