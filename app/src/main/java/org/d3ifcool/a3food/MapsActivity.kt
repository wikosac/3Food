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
import org.d3ifcool.a3food.data.Food


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
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(-6.973438, 107.631225), 16.5f)
        )
        enableMyLocation()

        //marker
        //fit
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.972791, 107.632497)
                )
                .title("WarmingUp")
        )
        //feb
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.971553, 107.632416)
                )
                .title("Kantin OpLib")
        )
        //kancow
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.969978, 107.627406)
                )
                .title("Kantin Asrama Putra")
        )
        //telcoff
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.973100, 107.629400)
                )
                .title("Telyu Coffee")
        )
        //gku
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.972681, 107.629645)
                )
                .title("Kantin GKU")
        )
        //kancew
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.974113, 107.629200)
                )
                .title("Kantin Asrama Putri")
        )
        //teknik
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.977475, 107.631066)
                )
                .title("Kantin Teknik")
        )
        //grill
        map.addMarker(
            MarkerOptions()
                .position(
                    LatLng(-6.972503, 107.632542)
                )
                .title("Telyou Grill")
        )

//        val cameraPosition = CameraPosition.Builder()
//            .target(LatLng(-6.920432082789247, 107.60370834146391))
//            .zoom(7F)
//            .build()
//        MapFragment.newInstance(
//            GoogleMapOptions()
//                .camera(cameraPosition)
//        )
        //my location
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//            val lokasi = LatLng(location.latitude, location.longitude)
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 17f))
//        }

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