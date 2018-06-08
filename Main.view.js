sap.ui.jsview("messageOverviewInfo.Main", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf messageOverviewInfo.Main
	*/ 
	getControllerName : function() {
		return "messageOverviewInfo.Main";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf messageOverviewInfo.Main
	*/ 
	createContent : function(oController) 
	{
		var button = new sap.ui.commons.Button({"text":" Call PI "});
		button.attachPress(oController.doIt);		
		return button;
		

		

	}

});
