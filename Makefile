default:
	echo  TODO

# ======= macros ======= 

# ======= targets ======= 

clean:
	rm -rf build
	rm -rf dist

install:
	# rm /sdcard/adhd_signed.apk
	# put self signed android package on "SD Card"
	cp -f ./dist/adhd_signed.apk /sdcard/
	# use activity manager to install/update app
	am start \
		--user 0 \
		-a android.intent.action.VIEW \
		-t application/vnd.android.package-archive \
		-d file:///sdcard/adhd_signed.apk

run:
	# use activity manager to start app
	am start \
		--user 0 \
		-a android.intent.action.MAIN \
		-n com.roboprogs.adhd/.MainActivity


# vim: ts=4 sw=4 ai
# *** EOF ***
