package ro.tudose.valentin.bickers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Valentin on 10/18/2018.
 */

public class PositionManager {

    private Context mContext;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private double longitude;
    private double latitude;
    private double altitude;

    PositionManager(Context context) {
        final PositionManager positionManager = this;
        this.mContext = context;
        this.locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        //TODO REFACTOR
        this.locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                positionManager.setLongitude(location.getLongitude());
                positionManager.setLatitude(location.getLatitude());
                positionManager.setAltitude(location.getAltitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                //TODO popup to activate gps
            }
        };

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationManager.requestLocationUpdates("gps", 1000, 2, locationListener);
    }


    double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    double getLatitude() {
        return latitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    private void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
