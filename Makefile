default:
	echo  TODO

# ======= macros ======= 

# ======= targets ======= 

clean:
	rm -rf build
	rm -rf dist

run:
	am start \
		--user 0 \
		-a android.intent.action.MAIN \
		-n com.roboprogs.adhd/.MainActivity


# vim: ts=4 sw=4 ai
# *** EOF ***
