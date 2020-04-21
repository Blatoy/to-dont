function removeUser(row) {
    let i = row.parentNode.parentNode.rowIndex;
    document.getElementById('users-table').deleteRow(i);
}


function addUser() {
    let table = document.getElementById('users-table');
    let tr = table.insertRow(-1);

    tr.innerHTML = '<td><label><input class="uk-checkbox" type="checkbox" name="moderatorBoolean[] value="1"><input type="hidden" name="moderatorBoolean[]" value="0"> Moderator</label></td>\
                    <td><button class="uk-button uk-button-small uk-close-large remove-user-button" onclick="removeUser(this)" type="button" uk-close></button></td>';



    let input = document.createElement('input');
    input.required = true;
    input.setAttribute('type', 'input');
    input.setAttribute('placeholder', 'Name');
    input.setAttribute('name', 'usernames[]');
    input.className = "uk-input"

    let td = tr.insertCell(0);
    td.appendChild(input);

    input.focus();
}