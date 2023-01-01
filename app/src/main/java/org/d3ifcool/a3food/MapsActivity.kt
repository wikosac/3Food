package org.d3ifcool.a3food

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {

//        val cameraPosition = CameraPosition.Builder()
//            .target(LatLng(-6.920432082789247, 107.60370834146391))
//            .zoom(7F)
//            .build()
//        MapFragment.newInstance(
//            GoogleMapOptions()
//                .camera(cameraPosition)
//        )

        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(-6.973838, 107.631625), 16.5f)
        )

        //my location
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//            val lokasi = LatLng(location.latitude, location.longitude)
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 17f))
//        }

        //marker
//        map.addMarker(
//            MarkerOptions()
//                .position(
//                    LatLng(-6.920432082789247, 107.60370834146391)
//                )
//                .title("Marker")
//        )

        enableMyLocation()
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this.applicationContext, ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResult: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResult.isNotEmpty() &&
                grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            }
        }
    }
}