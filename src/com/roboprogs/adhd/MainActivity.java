/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roboprogs.adhd;

import android.app.Activity;
import android.os.Bundle;

import org.library.*;

/**
 * Event handlers for Busy Box.
 */
public
class					MainActivity
	extends				Activity
	{

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

	}  // =================================================


// vim: ts=4 sw=4
// *** EOF ***
