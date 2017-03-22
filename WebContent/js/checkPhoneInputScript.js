/**
 * Created by Hp on 22.03.2017.
 */
function checkUserInputPhoneNumber (object)
{
    if (this.ST) return; var overPlace = object.value;
    var overLoad = overPlace.replace (/^[- +#]*[- +#0-9][- +#0-9]*/, '').length; this.ST = true;
    if (overLoad > 0) {
        object.value = object.lang; showError (object); return
    }
    object.lang = object.value; this.ST = null;
}
function showError (object)
{
    if (!this.OBJ)
    {
        this.OBJ = object;
        object.style.backgroundColor = 'pink';
        this.TIM = setTimeout (showError, 200)}
    else
    {
        this.OBJ.style.backgroundColor = '';
        clearTimeout (this.TIM); this.ST = null;
        checkUserInputPhoneNumber (this.OBJ); this.OBJ = null
    }
}
