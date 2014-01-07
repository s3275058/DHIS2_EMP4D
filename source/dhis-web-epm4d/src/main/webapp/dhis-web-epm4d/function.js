
    function addCertificate()
    {
        jQuery('#Cid').val(0);
        jQuery('#board').val('');
        jQuery('#subject').val('');
        jQuery('#trainingsystem').val('');
        jQuery('#startdate').val('');
        jQuery('#enddate').val('');
        jQuery('#candidate').val('');
    }

    function saveCertificate()
    {
        jQuery.ajax({
            url:'saveCertificate.action',
            data: jQuery('#frmCer').serialize(),
            success: function(html){
                arr = html.split(':');
                Cid = arr[1];

                if(jQuery('#Cid').val()>0) {//update}
                else {
                    //add new
                    row = "<tr><td></td><td>"+jQuery('#board').val()+"</td><td>"+jQuery('#subject').val()+"</td>"
                          +jQuery('#trainingsystem').val()+"</td><td>"+jQuery('#startdate').val()+"</td>"
                          +jQuery('#enddate').val()+"</td><td>"+jQuery('#candidate').val()+"</td>";
                    link = "<td><a onclick='editCertificate("+Cid+")'>Edit</a></td>";

                    row += link + "</tr>";

                    jQuery('tbody').append(row).fadeIn(1000);

                }
                jQuery('#Cid').val(arr[1]);
            }
        });
    }

    function editCertificate(Cid)
    {
        jQuery.ajax({
            url:'editCertificate.action',
            data: 'Cid='+Cid,
            success: function(html){

                arr = html.split('\n');

                jQuery('#Cid').val(arr[0].split(':')[1]);
                jQuery('#board').val(arr[1].split(':')[1]);
                jQuery('#subject').val(arr[2].split(':')[1]);
                jQuery('#trainingsystem').val(arr[3].split(':')[1]);
                jQuery('#startdate').val(arr[4].split(':')[1]);
                jQuery('#enddate').val(arr[5].split(':')[1]);
                jQuery('#candidate').val(arr[6].split(':')[1]);
            }
        });
    }

    function deleteCertificate(Cid)
    {
        if(confirm('Do you really want to delete?'))
        {
            jQuery.ajax({
                url:'deleteCertificate.action',
                data: 'Cid='+Cid,
                success: function(html){
                    jQuery("#tr_"+Cid).fadeOut(1000);
                    alert('Deleted successfully');
                }
            });
        }
    }
	
	    function login()
    {
        jQuery.ajax({
            type: 'POST',
            url: 'login.action',
            data: jQuery('#loginFrm').serialize(),
            success: function(html){
                alert(html);
            }
            error: function(html){
                alert("Wrong username or password");
            }
        });
    }
