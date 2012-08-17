package com.halcyonwaves.apps.energize.fragments;

import com.halcyonwaves.apps.energize.R;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutFragment extends Fragment {

	private final static String TAG = "AboutFragment";

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		View inflatedView = inflater.inflate( R.layout.fragment_about, container, false );

		// set the application version for the about screen
		try {
			TextView appVersionTextView = (TextView) inflatedView.findViewById( R.id.tv_about_application_version );
			appVersionTextView.setText( this.getString( R.string.textview_app_version, this.getActivity().getPackageManager().getPackageInfo( this.getActivity().getPackageName(), 0 ).versionName ) );
		} catch( NameNotFoundException e ) {
			Log.e( AboutFragment.TAG, "Cannot query the application version for setting it in the about screen." );
		}

		// ensure that a button click will open the play store for rating the app
		Button ratingButton = (Button) inflatedView.findViewById( R.id.btn_rate_app );
		ratingButton.setOnClickListener( new OnClickListener() {

			public void onClick( View v ) {
				try {
					Intent intent = new Intent( Intent.ACTION_VIEW );
					intent.setData( Uri.parse( "market://details?id=com.halcyonwaves.apps.energize" ) );
					AboutFragment.this.startActivity( intent );
				} catch( Exception e ) {
					Log.e( AboutFragment.TAG, "Failed to open the Google Play store to rate the application!" );
				}
			}
		} );

		return inflatedView;
	}
}
