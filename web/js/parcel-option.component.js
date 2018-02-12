// Register `phoneList` component, along with its associated controller and template
angular.
  module('parcelConfig').
  component('optionList', {
	  transclude: true,
	  template:
    	'<div class="w3-panel w3-card-2">'+
    		'<div class="w3-container w3-teal">'+
    			'<h2>Lieferoptionen</h2>'+
    		'</div>' +
	    	'<div class="w3-cell-row" style="width:75%">'+
	    		'<form class="w3-container" >'+
	    			'<div class="w3-row" ng-repeat="option in $ctrl.options">'+
	    				'<div class="w3-col m4 l9">'+
	    					'<p><input class="w3-radio" type="radio" name="option" ng-model="option.checked">'+
	    					'<label>{{option.name}}</label></p>'+
	    				'</div>'+
	    				'<div class="w3-col m8 l3"><p>{{option.price}} EUR</p></div>'+
	    			'</div>'+
	    		'</form>'+
	    	'</div>'+
		'</div>',
    	
    controller: function ParcelOptionController($rootScope) {
      this.options = [
        {
          name: 'Abgabe im Paketshop',
          price: 0.00,
          checked: false
        }, {
          name: 'Abholung an der Haustüre',
          price: 4.00,
          checked: false
        }
      ];
      $rootScope.parceloptions = this.options;
    }
  });
  
 
  