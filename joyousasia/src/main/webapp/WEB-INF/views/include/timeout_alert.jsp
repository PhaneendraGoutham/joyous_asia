<style>
.modal-backdrop.fade.in
{
	z-index:0 !important;
}
</style>

<script>
function constructString(strMessage) {
	var strMsg = "<ul>";
	if (strMessage instanceof Array) {
		for (var intLoop = 0; intLoop < strMessage.length; intLoop++) {
			strMsg += "<li>" + strMessage[intLoop] + "</li>";
		}
	} else {
		strMsg += "<li>" + strMessage + "</li>";
	}

	strMsg += "</ul>";

	return strMsg;
}

var gritter_id;

function alertNotification(strMessage) {
	if (strMessage == null)
		return;

	var constructedMsg = constructString(strMessage);

	$.gritter.add({
		//	(string | mandatory) the heading of the notification
		title : "Notification",
		//	(string | mandatory) the text inside the notification
		text : constructedMsg,
		//	image: '${ WEBAPPS }/img/alert.gif',
		class_name : "gritter"
	});
}

window.alertNotification = alertNotification;

$.urlParam = function(name, url) {
    if (!url) {
     url = window.location.href;
    }
    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(url);
    if (!results) { 
        return undefined;
    }
    return results[1] || undefined;
};


</script>


<body></body>
