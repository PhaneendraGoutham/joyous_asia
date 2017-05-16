<%@ include file="../include/import.jsp"%>
<%@ include file="../include/timeout_alert.jsp"%>

<div id="sidebar">
	<ul>
		
		<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("customer")>=0 ){ %> class="active submenu open" <% } else { %> class="submenu" <% } %> >
			<a href="#"><i class="fa fa-group"></i><span>Customer Mnagement</span><i class="arrow fa fa-chevron-right"></i></a>
			<ul>
				<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("customer/list.jsp")>=0){ %> class="active" <% } else { %> class="" <% } %> >
				<a href="${ WEBAPPS }/customer/list${ SPRING_EXT }">View Customer</a></li>
			
				<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("customer/add.jsp")>=0){ %> class="active" <% } else { %> class="" <% } %> >
				<a href="${ WEBAPPS }/customer/add${ SPRING_EXT }">Add Customer</a></li>
				
			</ul>
		</li>
		
		<li>
			<a href="${ WEBAPPS }/item/list${ SPRING_EXT }"><i class="fa fa-gift"></i><span>Item Mnagement</span></a>
		</li>
		
		<li>
			<a href="${ WEBAPPS }/csv/upload${ SPRING_EXT }"><i class="fa fa-upload"></i><span>Upload CSV</span></a>
		</li>
		
		<li>
			<a href="${ WEBAPPS }/invoice/config${ SPRING_EXT }"><i class="fa fa-file-text-o"></i><span>Invoice Configuration</span></a>
		</li>
		
		<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("event")>=0 ){ %> class="active submenu open" <% } else { %> class="submenu" <% } %> >
			<a href="#"><i class="fa fa-cog"></i><span>Event Configuration</span><i class="arrow fa fa-chevron-right"></i></a>
			<ul>
				<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("event/event_config.jsp")>=0){ %> class="active" <% } else { %> class="" <% } %> >
				<a href="${ WEBAPPS }/event/event_config${ SPRING_EXT }">Event</a></li>
			
				<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("event/school_config.jsp")>=0){ %> class="active" <% } else { %> class="" <% } %> >
				<a href="${ WEBAPPS }/event/school_config${ SPRING_EXT }">School</a></li>
				
				<li <% if( String.valueOf( request.getAttribute( "MYPAGE" ) ).indexOf("event/faculty_config.jsp")>=0){ %> class="active" <% } else { %> class="" <% } %> >
				<a href="${ WEBAPPS }/event/faculty_config${ SPRING_EXT }">Faculty</a></li>
			</ul>
		</li>
	</ul>
</div>