/**
 * Energize - An Android battery monitor
 * Copyright (C) 2012 Tim Huetz
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.halcyonwaves.apps.energize;

import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class ApplicationCore extends Application {

	public static boolean isServiceRunning( Context ctx, String serviceName ) {
		Log.v( "ApplicationCore", "Checking if the monitoring service is running or not..." );
		boolean serviceRunning = false;
		ActivityManager am = (ActivityManager) ctx.getSystemService( ACTIVITY_SERVICE );
		List< ActivityManager.RunningServiceInfo > l = am.getRunningServices( 50 );
		Iterator< ActivityManager.RunningServiceInfo > i = l.iterator();
		while( i.hasNext() ) {
			ActivityManager.RunningServiceInfo runningServiceInfo = (ActivityManager.RunningServiceInfo) i.next();

			if( runningServiceInfo.service.getClassName().equals( serviceName ) ) {
				serviceRunning = true;
			}
		}
		return serviceRunning;
	}

	public static int getSelectedThemeId( final Context context ) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
		final String d = prefs.getString( "display.theme", "UnknownTheme" );
		if( d.compareTo( "DarkTheme" ) == 0 ) {
			return R.style.DarkTheme;
		} else if( d.compareTo( "LightTheme" ) == 0 ) {
			return R.style.LightTheme;
		} else {
			Log.w( "BatteryStateDisplayActivity.Theme", "The selected theme is unknown ('" + d + "'), returning default theme!" );
			return R.style.DarkTheme;
		}
	}
}
