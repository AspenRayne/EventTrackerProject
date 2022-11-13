window.addEventListener('load', function() {
	console.log('script.js loaded');
	init();
});

function init() {
	//TODO: setup event listener
	//TODO: load inital data.
	loadConcertList();
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

	if (concerts && Array.isArray(concerts) && concerts.length > 0) {

		let table = document.createElement('table');
		table.className = "table table-dark table-hover table-bordered bordered-light";
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
		th = document.createElement('th');
		th.textContent = "Concert Date";
		tr.appendChild(th);
		th = document.createElement('th');
		th.textContent = "Ticket Url";
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
			td = document.createElement('td');
			td.textContent = val.concertDate;
			tr.appendChild(td);
			td = document.createElement('td');
			let a = document.createElement('a');
			let link = document.createTextNode("Get Tickets");
			a.appendChild(link);
			a.href = val.ticketUrl;
			td.appendChild(a);
			tr.appendChild(td);
			
			tr.addEventListener('click', function(){
				displayPerformers(val.performers);
				
			
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

	let userSearch = [
		{
			type: "state",
			query: state
		},
		{
			type: "city",
			query: city
		},
		{
			type: "performer",
			query: performer
		}
	];

	console.log(userSearch[0]);

	let userObject = [];

	userSearch.forEach(element => {
		if (element.query !== "") {
			userObject.push(element);
			console.log(userObject)
		}
		let userObjectJson = JSON.stringify(userObject);
		console.log(userObjectJson);
		searchSeatGeek(userObjectJson);


	});


	form.reset();
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
				displayPerformers(performers);
			} else if (xhr.status === 404) {
				displayError('Performers not found');
			} else {
				displayError('An error occurred: ' + xhr.status);
			}
		}
	};

	xhr.send();
}

function displayPerformers(performers) {
	let performerData = document.getElementById('performerListDiv');
	performerData.textContent = '';
	if (performers && performers.length > 0) {
		let h3 = document.createElement('h3');
		h3.textContent = 'Performers'
		performerData.appendChild(h3);
		let ul = document.createElement('ul');
		ul.className = "list-group list-group-flush"
		performerData.appendChild(ul);
		performers.forEach(function(val) {
			let li = document.createElement('li');
			const img = document.createElement('img');
			img.src = val.imageUrl;
			console.log(img.src);
			li.className = "list-group-item"
			li.textContent = val.name;
			li.appendChild(img)
			ul.appendChild(li);
		})
	}
}
