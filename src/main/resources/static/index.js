const $result = $('#result');
const $error = $('#error');

$(document).ready(function () {
    $('#main-form').on('submit', function (e) {
        e.preventDefault();
        populateResult();
    });

    $('#result').isotope({
        itemSelector: '.result-item',
        layoutMode: 'packery',
        packery: {
            gutter: 15
        }
    });

    $('.filter-button-group').on('click', 'button', function () {
        let filterValue = $(this).attr('data-filter');
        $result.isotope({filter: filterValue});
    });
});

function populateResult() {
    let inputString = $('#inputString').val().trim().replace(/\n/g, " ");

    $result.isotope('remove', $result.children()).isotope('layout');
    $error.addClass('d-none');

    if (inputString.length > 0) {
        let url = `/api/parse?inputString=${encodeURIComponent(inputString)}`;
        fetch(url).then(response => response.json())
            .then(jsonResponse => addResultToDOM(jsonResponse.result))
            .catch(error => showErrorMessage(error));
    } else {
        showErrorMessage("Please enter a statement");
    }
}

function showErrorMessage(message) {
    $error.html(`<h1 class="text-danger">${message}</h1>`);
    $error.removeClass("d-none");
}

function addResultToDOM(result) {
    let $cards = $(result.map(tokenDetails => getCard(tokenDetails)).join("\n"));
    $result.append($cards).isotope('appended', $cards);
    $result.isotope({filter: '*'});
}

function getStateTransition(startState, endState, inputKey) {
    if (endState === 'q-1')
        endState = 'invalid';
    return `
        <li class="list-group-item">
            <span class="badge badge-pill badge-primary p-3"> ${startState.padStart(3, ' ')} </span>
            <span class="btn btn-outline-dark"> ${inputKey} </span>
             <span class="lead"> &#8702;</span>
            <span class="badge badge-pill badge-${endState === 'invalid' ? 'danger' : 'success'} p-3"> ${endState.padStart(3, ' ')} </span>
        </li>
    `;
}

function getVisitedStates(token, visitedStates) {
    let states = visitedStates.map(state => state.state);
    let listGroupItems = [];
    for (let i = 0; i < token.length; i++) {
        let currentChar = token.charAt(i);
        let startState = states[i];
        let endState = states[i + 1];

        if (startState === 'q-1')
            break;

        listGroupItems.push(getStateTransition(startState, endState, currentChar));
    }

    return `
            <ul class="list-group">
              ${listGroupItems.join(' ')}
            </ul>`;
}

function getCard(tokenDetails) {
    let {tokenText, tokenType, visitedStates} = tokenDetails;
    let visitedStatesGroup = getVisitedStates(tokenText, visitedStates);
    return `
            <div class="p-3 card col-md-4 mx-auto p-3 result-item ${tokenType.toLowerCase()}">
                <div class="card-body p-2 text-center">
                    <h5 class="card-title display-4 text-center">${tokenText}</h5>
                    <b>Token Type: </b>${tokenType}<br>
                    <b>Visited States</b>
                    ${visitedStatesGroup}
                </div>
            </div>
        `;
}


