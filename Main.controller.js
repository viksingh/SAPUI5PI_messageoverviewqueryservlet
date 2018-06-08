sap.ui.controller("messageOverviewInfo.Main", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf helloworldui5.Main
*/
	onInit: function() {
	
	console.log("Starting ");

	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf helloworldui5.Main
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf helloworldui5.Main
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf helloworldui5.Main
*/
//	onExit: function() {
//
//	}
	
	doIt : function(oEvent) {
		
		 $.get('ReadUsageData', {             
          }, function(responseText) {
           alert(responseText);       
});	

}});
