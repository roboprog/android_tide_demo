default:
	echo  TODO

# ======= macros ======= 

APP_SRC_PKG=src/com/roboprogs/adhd
APP_CLS_PKG=build/classes/com/roboprogs/adhd

# ======= targets ======= 

clean:
	rm -rf build/classes/*
	rm -rf dist/*
	rm $(APP_SRC_PKG)/R.java

# mk_bld_dirs:
#	mkdir -m 770 -p dist
#	mkdir -m 770 -p build/classes

$(APP_SRC_PKG)/R.java : AndroidManifest.xml \
		res/layout/main.xml \
		res/values/strings.xml
	aapt p \
		-f \
		-v \
		-M AndroidManifest.xml \
		-F ./build/resources.res \
		-I ~/system/classes/android.jar \
		-S res/ \
		-J $(APP_SRC_PKG)

$(APP_CLS_PKG)/MainActivity.class : $(APP_SRC_PKG)/*.java
	( cd src ; \
	javac -verbose \
		-cp ../libs/demolib.jar \
		-d ../build/classes \
		com/roboprogs/adhd/*.java )

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
