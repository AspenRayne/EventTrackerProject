window.addEventListener('load', function() {
	console.log('script.js loaded');
	init();
});

function init() {
	//TODO: setup event listener
	//TODO: load inital data.
	document.getElementById('savedConcerts').addEventListener('click', loadConcertList);

	searchSG.search.addEventListener('click', submitCallBack)
};

function loadConcertList() {
	//TODO; XHR to get the list
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/concerts');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let concertJson = xhr.responseText;
				console.log(concertJson); // TESTING
				let concerts = JSON.parse(concertJson);
				displayConcertList(concerts);
				concerts.forEach(function(val) {
					getPerformers(val.seatGeekId);
				});

			} else if (xhr.status === 404) {
				displayError('Concerts not found');
			} else {
				displayError('An error occurred: ' + xhr.status);
			}
		}
	};

	xhr.send();

}

function displayConcertList(concerts) {
	//TODO: add concerts to DOM
	let dataDiv = document.getElementById('concertListDiv');
	dataDiv.textContent = '';
	
	let div = document.getElementById('performerListDiv');
	div.textContent = "";

	if (concerts && Array.isArray(concerts) && concerts.length > 0) {

		let table = document.createElement('table');
		table.className = "table table-primary table-hover table-bordered bordered-light";
		dataDiv.appendChild(table);
		let thead = document.createElement('thead');
		table.appendChild(thead);
		let tbody = document.createElement('tbody');
		table.appendChild(tbody)

		let tr = document.createElement('tr');
		thead.appendChild(tr);
		let th = document.createElement('th');
		th.textContent = "Title";
		tr.appendChild(th);
		th = document.createElement('th');
		th.textContent = "Venue";
		tr.appendChild(th);
		th = document.createElement('th');
		th.textContent = "Venue Location";
		tr.appendChild(th);

		concerts.forEach(function(val) {
			tr = document.createElement('tr');
			tbody.appendChild(tr);
			let td = document.createElement('td');
			td.textContent = val.title;
			tr.appendChild(td);
			td = document.createElement('td');
			td.textContent = val.venue.name;
			tr.appendChild(td);
			td = document.createElement('td');
			td.textContent = val.venue.city + ", " + val.venue.state + " " + val.venue.country + " " + val.venue.postalCode;
			tr.appendChild(td);

			tr.addEventListener('click', function() {
				displayPerformers(val.performers, val);
			});
		});
	}
}


let submitCallBack = function(e) {
	e.preventDefault();
	let form = e.target.parentElement;

	let city = form.city.value;
	let state = form.state.value;
	let performer = form.performer.value;

	let userSearch = [];
	if (city !== "") {
		userSearch.push({
			type: "city",
			query: city
		});
	}
	if (state !== "") {
		userSearch.push({
			type: "state",
			query: state
		});
	}
	if (performer !== "") {
		userSearch.push({
			type: "performer",
			query: performer
		});
	}

	console.log(userSearch[0]);

	let userObjectJson = JSON.stringify(userSearch);
	console.log(userObjectJson);
	searchSeatGeek(userObjectJson);

	form.reset();
	let div = document.getElementById('performerListDiv');
	div.textContent = "";
}

function searchSeatGeek(userSearch) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/searchSG/concerts');
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let data = JSON.parse(xhr.responseText);
				displayConcertList(data);
			} else {
				//displayError('An error occurred: ' + xhr.status);
			}
		}
	};

	xhr.send(userSearch);

}

function getPerformers(id) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', `api/concerts/${id}/performers`);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let performerJson = xhr.responseText;
				console.log(performerJson); // TESTING
				let performers = JSON.parse(performerJson);

			} else if (xhr.status === 404) {
				displayError('Performers not found');
			} else {
				displayError('An error occurred: ' + xhr.status);
			}
		}
	};

	xhr.send();
}

function displayPerformers(performers, val) {
	let dataDiv = document.getElementById('performerListDiv');
	dataDiv.textContent = '';

	let mainRowDiv = document.createElement('div');
	mainRowDiv.className = 'row';
	dataDiv.appendChild(mainRowDiv);

	let mainColDiv = document.createElement('div');
	mainColDiv.className = 'col';
	mainRowDiv.appendChild(mainColDiv);

	let mainColDiv2 = document.createElement('div');
	mainColDiv2.className = 'col';
	mainRowDiv.appendChild(mainColDiv2);


	if (performers && performers.length > 0) {

		performers.forEach(function(val) {

			let cardDiv = document.createElement('div');
			cardDiv.className = 'card mb-3';
			cardDiv.style = 'max-width: 600px'
			mainColDiv.appendChild(cardDiv);

			let rowDiv = document.createElement('div');
			rowDiv.className = 'row g-0';
			cardDiv.appendChild(rowDiv);

			let imageDiv = document.createElement('div');
			imageDiv.className = 'col-md-4';
			rowDiv.appendChild(imageDiv);

			let img = document.createElement('img');
			img.src = val.imageUrl;
			img.className = 'img-fluid rounded-start';
			imageDiv.appendChild(img);

			let colDiv = document.createElement('div');
			colDiv.className = 'col-md-8';
			rowDiv.appendChild(colDiv);

			let bodyDiv = document.createElement('div');
			bodyDiv.className = 'card-body';
			colDiv.appendChild(bodyDiv);

			let h5 = document.createElement('h5');
			h5.className = 'card-title';

			let textNode = document.createTextNode('Perfomer: ' + val.name);
			h5.appendChild(textNode);
			bodyDiv.appendChild(h5);


		})
		let ul = document.createElement('ul');
		ul.className = 'list-group list-group-flush';
		mainColDiv2.appendChild(ul);

		let li = document.createElement('li');
		li.className = 'list-group-item';
		li.textContent = 'Venue: ' + val.venue.name;
		ul.appendChild(li);

		li = document.createElement('li');
		li.className = 'list-group-item';
		li.textContent = 'Venue Location: ' + val.venue.city + ", " + val.venue.state + " " + val.venue.country + " " + val.venue.postalCode;
		ul.appendChild(li);

		li = document.createElement('li');
		li.className = 'list-group-item';
		li.textContent = 'Concert Date: ' + val.concertDate;
		ul.appendChild(li);

		li = document.createElement('li');
		li.className = 'list-group-item';
		let a = document.createElement('a');
		let link = document.createTextNode("Get Tickets");
		a.appendChild(link);
		a.href = val.ticketUrl;
		li.appendChild(a);
		ul.appendChild(li);

		li = document.createElement('li');
		li.className = 'list-group-item';

		if (val.id !== 0) {
			let button = document.createElement('button');
			button.className = 'btn btn-danger';
			let textNode = document.createTextNode('Unsave Concert');
			button.appendChild(textNode);
			button.addEventListener('click', function(){
				removeConcert(val.id)
				});
			li.appendChild(button)
			ul.appendChild(li);

		} if (val.id === 0) {
			let button = document.createElement('button');
			button.className = 'btn btn-success';
			let textNode = document.createTextNode('Save Concert');
			button.appendChild(textNode);
			button.addEventListener('click', function(){
				saveConcert(val.seatGeekId)
				});
			li.appendChild(button)
			ul.appendChild(li);
			
		}
	}

}



function saveConcert(seatGeekId) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', `api/concerts/${seatGeekId}`);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) {
				let data = JSON.parse(xhr.responseText);
				loadConcertList();
			}
			else {
				displayError('Error creating film: ' + xhr.status + " " + xhr.statusText)
			}
		}
	};

	xhr.send();
}

function removeConcert(id) {
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', `api/concerts/${id}`);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 204 || xhr.status == 200) {
				loadConcertList();
			}
			else {
				displayError('Error creating film: ' + xhr.status + " " + xhr.statusText)
			}
		}
	};

	xhr.send();
}


