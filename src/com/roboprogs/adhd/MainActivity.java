/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roboprogs.adhd;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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

	/** handle to logging panel */
	private
	TextView			logBox;

	/** accumulated log (emulation) text */
	private
	String				logText = "";

	/** media player instance to manage for noises */
	private
	MediaPlayer			player;

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
		wireEvents();
		info( "Event handlers in place");
		}  // _____________________________________________

	/**
	 * Set up event handlers for screen controls
	 */
	private
	void				wireEvents()
		{
		SeekBar.OnSeekBarChangeListener
						seekHandler;

		this.logBox = (TextView) findViewById( R.id.busy_log);
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
				info( "slider moved");
				play( Settings.System.DEFAULT_NOTIFICATION_URI);
				MainActivity.this.sliderPos = progress;
				updateStatus();
				}  // _____________________________________

			public
			void		onStartTrackingTouch( SeekBar bar)
				{}
			public
			void		onStopTrackingTouch( SeekBar bar)
				{}
			} ;  // =======================================

		this.seeker.setOnSeekBarChangeListener( seekHandler);
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
		info( "button clicked");
		// play( Settings.System.DEFAULT_RINGTONE_URI);
		play( Settings.System.DEFAULT_ALARM_ALERT_URI);
		this.btnClicks++;
		updateStatus();
		}  // _____________________________________________

	/**
	 * Update status text at bottomm of Busy-Box.
	 */
	private
	void				updateStatus()
		{
		TextView		text;
		int				alpha;
		int				r;
		int				g;
		int				b;
		int				color;

		this.logBox.setText( this.logText);
		text = (TextView) findViewById( R.id.busy_text);
		r = ( ( this.sliderPos & 0x04) >> 2);
		g = ( ( this.sliderPos & 0x02) >> 1);
		b = ( this.sliderPos & 0x01);
		alpha = 0x77000000;
		color = alpha + ( r * 0xaa0000) + ( g * 0x00aa00) + ( b * 0x0000aa);
		text.setTextColor( color);
		text.setText( "Click count: " + this.btnClicks +
				", Pos: " + this.sliderPos);
		}  // _____________________________________________

	/**
	 * Play a sound.
	 */
	private
	void				play
		(
		Uri				resource
		)
		{
		if ( this.player != null)
			{
			this.player.reset();
			}   // previous instance set up?
		// else:  nothing to clean up
		this.player = MediaPlayer.create( this, resource);
		this.player.start();
		}  // _____________________________________________

	/**
	 * Spout a log mmessage.
	 *  TODO: copy the text into a scrolling text box control, as well.
	 */
	private
	void				info
		(
		String			msg
		)
		{
		Log.i( MainActivity.class.getName(), msg);

		// adb + logcat display other activity, but not our msgs,
		//  so implement a workaround
		// (newest on top, so don't have to scroll down)
		this.logText = ( "I) " + msg + "\n" + this.logText);
		// TODO: timestamp
		updateStatus();
		}  // _____________________________________________

	}  // =================================================


// vim: ts=4 sw=4
// *** EOF ***
