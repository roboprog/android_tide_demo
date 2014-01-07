#Script to start the Andoid app

#'am' is found on most/all devices.. i hope..
am start \
	--user 0 \
	-a android.intent.action.MAIN \
	-n org.me.androiddemo/.MainActivity
