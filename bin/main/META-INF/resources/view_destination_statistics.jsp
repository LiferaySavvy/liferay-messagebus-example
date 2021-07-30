


<%@page import="com.liferay.portal.kernel.messaging.DestinationStatistics"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>
<portlet:actionURL name="/destination/statistics" var="destinationStatisticsURL" />
<%
List<SelectOption> selectOptions = new ArrayList<>();
selectOptions.add(new SelectOption("liferaysavvy/parallel-destination", String.valueOf("liferaysavvy/parallel-destination")));
selectOptions.add(new SelectOption("liferaysavvy/serial-destination", String.valueOf("liferaysavvy/serial-destination")));
selectOptions.add(new SelectOption("liferaysavvy/synchronous-destination", String.valueOf("liferaysavvy/synchronous-destination")));
%>

	
<aui:form action="<%= destinationStatisticsURL %>" cssClass="edit-entry" enctype="multipart/form-data" method="post" name="fm">
<aui:fieldset-group markupView="lexicon">
<aui:fieldset>
		<div class="clearfix">
			<clay:select label="Destination" name="<%=curPortletNameSpace+"destination"%>" options="<%= selectOptions %>"/>
		</div>
	</aui:fieldset>

	<aui:fieldset>
		<div class="clearfix">
			<aui:button name="GetStatistics" type="submit" value="Get Statistics" />
		</div>
	</aui:fieldset>
	
</aui:fieldset-group>

</aui:form>


<%
DestinationStatistics dstats=(DestinationStatistics)request.getAttribute("destinationStats");
List<String> listeners = (List<String>)request.getAttribute("listeners");
%>
<%if(dstats !=null){ %>
<h2>Display Employee Details</h2><br/>
<b>Active Thread Count  : </b><%=dstats.getActiveThreadCount()%>    <br/>
<b>Current Thread Count : </b><%=dstats.getCurrentThreadCount()%>    <br/>
<b>Largest Thread Count : </b><%=dstats.getLargestThreadCount()%>    <br/>
<b>Max Thread Pool Size : </b><%=dstats.getMaxThreadPoolSize()%>    <br/>
<b>Min Thread Pool Size : </b><%=dstats.getMinThreadPoolSize()%>    <br/>
<b>Pending Message Count: </b><%=dstats.getPendingMessageCount()%>    <br/>
<b>Sent Message Count   : </b><%=dstats.getSentMessageCount()%>    <br/>
<%} %>

<%if(listeners !=null){ %>
<h2>Registered Listeners</h2><br/>

<%for(String listner: listeners){%>

<b><%=listner%></b>    <br/>
<%}} %>

<portlet:renderURL var="homeURL">
</portlet:renderURL>
<br/>
<br/>
<aui:button href="<%= homeURL %>" value="Home" />