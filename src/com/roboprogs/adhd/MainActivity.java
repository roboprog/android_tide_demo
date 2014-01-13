/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roboprogs.adhd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.library.*;

/**
 * Event handlers for Busy Box.
 */
public
class					MainActivity
	extends				Activity
	{

	/** count the button clicks/presses */
	private
	int					btnClicks = 0;

    /** Called when the activity is first created. */
    @Override
    public
	void				onCreate
		(
		Bundle			icicle
		)
		{
        super.onCreate( icicle);

        setContentView( R.layout.main);
		}  // _____________________________________________

	/**
	 * Count each time button is clicked/pressed.
	 *  (should be connected to event handler via layout XML)
	 */
	public
	void				countClicks
		(
		View			unused
		)
		{
		TextView		text;

		this.btnClicks++;
		text = (TextView) findViewById( R.id.busy_text);
		text.setText( "Click count: " + this.btnClicks);
		}  // _____________________________________________

	}  // =================================================


// vim: ts=4 sw=4
// *** EOF ***
