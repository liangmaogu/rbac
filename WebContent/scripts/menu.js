$(document).ready(function() {
	$.jstree._themes = "scripts/jstree/themes/";
	
	childrenNode("menu-tree", "0");
	
});

function childrenNode(nodeId, id) {
	$.ajax({
		url: "backend/Resource/getAllMenu.do",
		data: "id="+id,
		success: function(data) {
			var menus = eval("(" + data.trim() + ")");
					
			$("#"+nodeId).jstree({
				"plugins": ["themes","json_data","ui", "crrm", "hotkeys", "contextmenu"],
				"json_data": {
					"data": menus
				}
			}).bind("select_node.jstree", function (event, data) {
				var url = data.rslt.obj.data("url");

				window.location.href = url;
				
				var nodeId = data.rslt.obj.data("id");
				var id = nodeId.replace("node_", ""); 
				childrenNode(nodeId, id);
			});
		}
	});
}
