function checkUserInput (object)
{
    if (this.ST) return;
    var overPlace = object.value;
    var overLoad = overPlace.replace (/^[a-zA-Z0-9а-яА-Я_-]*$/, '').length; this.ST = true;

    if (overLoad > 0) {
        object.value = object.lang; showError (object);
        return
    }
    object.lang = object.value; this.ST = null;
}

function showError (object)
{
    if (!this.OBJ)
    {
        this.OBJ = object; object.style.backgroundColor = 'pink';
        this.TIM = setTimeout (showError, 200)}
    else
    {
        this.OBJ.style.backgroundColor = '';
        clearTimeout (this.TIM); this.ST = null;
        checkUserInputPhoneNumber (this.OBJ);
        this.OBJ = null
    }
}