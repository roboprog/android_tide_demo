/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roboprogs.adhd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
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

	/** handle to slider progress bar */
	private
	SeekBar				seeker;

	/** track slider position */
	private
	int					sliderPos = 0;

    /** Called when the activity is first created. */
    @Override
    public
	void				onCreate
		(
		Bundle			icicle
		)
		{
        super.onCreate( icicle);

		SeekBar.OnSeekBarChangeListener
						seekHandler;

		info( "About to create something");

        setContentView( R.layout.main);

		// *now* try wiriing up even handlers

		this.seeker = (SeekBar) findViewById( R.id.busy_seek);

		seekHandler = new SeekBar.OnSeekBarChangeListener()
			{

			/**
			 * Track progress bar slider updates.
			 */
			public
			void		onProgressChanged
				(
				SeekBar bar,
				int		progress,
				boolean fromUser
				)
				{
/*
				MainActivity.this.sliderPos = progress;
				updateStatus();
*/
				}  // _____________________________________

			public
			void		onStartTrackingTouch( SeekBar bar)
				{}
			public
			void		onStopTrackingTouch( SeekBar bar)
				{}
			} ;  // =======================================

		// this.seeker.setOnSeekBarChangeListener( seekHandler);

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
		this.btnClicks++;
		updateStatus();
		}  // _____________________________________________

	/**
	 * Update status text at bottomm of Busy-Box.
	 */
	public
	void				updateStatus()
		{
		TextView		text;

		text = (TextView) findViewById( R.id.busy_text);
		text.setText( "Click count: " + this.btnClicks +
				", Pos " + this.sliderPos +
				", Seeker = " + this.seeker);
		}  // _____________________________________________

	/**
	 * Spout a log mmessage.
	 */
	public
	void				info
		(
		String			msg
		)
		{
		Log.i( MainActivity.class.getName(), msg);
		}  // _____________________________________________

	}  // =================================================


// vim: ts=4 sw=4
// *** EOF ***
