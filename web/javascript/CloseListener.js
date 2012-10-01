/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function handleLocalesDialogComplete(xhr, status, args, dialog)
{
    if (args) {
        var isValid = args.isValid;
        if(!isValid) {
            dialog.hide();
        }
    }
}