function removeUser(row) {
    var i = row.parentNode.parentNode.rowIndex;
    document.getElementById('users-table').deleteRow(i);
}


function addUser() {
    var table = document.getElementById('users-table');
    var tr = table.insertRow(-1);

    tr.innerHTML = '<td><label><input class="uk-checkbox" type="checkbox"> Moderator</label></td>\
                    <td><button class="uk-button uk-button-small uk-close-large remove-user-button" onclick="removeUser(this)" type="button" uk-close></button></td>';



    var input = document.createElement('input');
    input.required = true;
    input.setAttribute('type', 'input');
    input.setAttribute('placeholder', 'Name');
    input.className = "uk-input"

    var td = tr.insertCell(0);
    td.appendChild(input);

    input.focus();
}