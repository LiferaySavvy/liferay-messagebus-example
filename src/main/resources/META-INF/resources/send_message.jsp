

<%@ include file="/init.jsp" %>
<portlet:actionURL name="/send/message" var="sendMessageURL" />
<%
	List<SelectOption> selectOptions = new ArrayList<>();
	selectOptions.add(new SelectOption("liferaysavvy/parallel-destination", String.valueOf("liferaysavvy/parallel-destination")));
	selectOptions.add(new SelectOption("liferaysavvy/serial-destination", String.valueOf("liferaysavvy/serial-destination")));
	selectOptions.add(new SelectOption("liferaysavvy/synchronous-destination", String.valueOf("liferaysavvy/synchronous-destination")));
%>

	
<aui:form action="<%= sendMessageURL %>" cssClass="edit-entry" enctype="multipart/form-data" method="post" name="fm">
<aui:fieldset-group markupView="lexicon">
<aui:fieldset>
		<div class="clearfix">
			<clay:select label="Destination" name="<%=curPortletNameSpace+"destination"%>" options="<%= selectOptions %>"/>
		</div>
	</aui:fieldset>
	<aui:fieldset>
		<div class="clearfix">
			<aui:input label="Message" name="<%=curPortletNameSpace+"message"%>"  type="textarea" value="" />
		</div>
	</aui:fieldset>
	<aui:fieldset>
		<div class="clearfix">
			<aui:button name="saveButton" type="submit" value="Send Message" />
		</div>
	</aui:fieldset>
	
</aui:fieldset-group>

</aui:form>

<portlet:renderURL var="homeURL">
</portlet:renderURL>
<br/>
<br/>
<aui:button href="<%= homeURL %>" value="Home" />

