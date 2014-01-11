#!/system/bin/sh -x

#Install the apk

#cd into the home dir - this way it works when run from inside vim or any other folder
cd ~/proj/adhd

#Remove the Old
if [ -f /sdcard/adhd_signed.apk ];then
	rm /sdcard/adhd_signed.apk
fi

#Only works if APK is on the sdcard
cp ./dist/adhd_signed.apk /sdcard/

#Now try and view it..
am start \
	--user 0 \
	-a android.intent.action.VIEW \
	-t application/vnd.android.package-archive \
	-d file:///sdcard/adhd_signed.apk
