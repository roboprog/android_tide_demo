default: build

# note that install will prompt to run, anyway
all: clean build install # run

# ======= macros ======= 

# directory for the Java files
APP_SRC_PKG=src/com/roboprogs/adhd

# directory for the class files
APP_CLS_PKG=build/classes/com/roboprogs/adhd

# sample class file (all built at same time)
SAMPLE_CLASS=$(APP_CLS_PKG)/MainActivity.class

# ======= targets ======= 

clean:
	rm -rf build/classes/*
	rm -f build/*.*  # avoid carping about subdir
	rm -rf dist/*
	rm -f $(APP_SRC_PKG)/R.java

build: dist/adhd_signed.apk

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

$(SAMPLE_CLASS) : $(APP_SRC_PKG)/*.java \
		$(APP_SRC_PKG)/R.java
	mkdir -p build/classes
	( cd src ; \
	javac -verbose \
		-cp ../libs/demolib.jar \
		-d ../build/classes \
		com/roboprogs/adhd/*.java )

build/adhd.dex : $(SAMPLE_CLASS)
	# jvm/class to dex ('droid executable) conversion
	( cd build/classes ; \
	dx --dex \
		--verbose \
		--no-strict \
		--output=../adhd.dex \
		com \
		../../libs/demolib.jar )

dist/adhd.apk : build/adhd.dex \
		build/resources.res \
		$(APP_SRC_PKG)/R.java
	# android packager
	apkbuilder \
		dist/adhd.apk \
		-v \
		-u \
		-z build/resources.res \
		-f build/adhd.dex 

dist/adhd_signed.apk : dist/adhd.apk
	( cd dist ; \
	signer adhd.apk adhd_signed.apk )

install: build
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
