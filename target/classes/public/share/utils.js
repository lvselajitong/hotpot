function notify(message, type, dismiss) {
	var delay;
	if(type === 'success'){
		delay = 5000;
	}else{
		delay = 0;
	}
    $.notify(
        { message: message },
        {
            allow_dismiss: dismiss,
            delay: delay,
            type: type,   
            placement: { from: 'bottom', algin: 'right' },
            animate: { enter: 'animated fadeInUp', exit: 'animated fadeOutDown' }
        });
}