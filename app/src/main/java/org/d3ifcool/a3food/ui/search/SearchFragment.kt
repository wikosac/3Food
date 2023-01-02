package org.d3ifcool.a3food.ui.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import org.d3ifcool.a3food.DetailActivity
import org.d3ifcool.a3food.MainActivity
import org.d3ifcool.a3food.MapsActivity
import org.d3ifcool.a3food.databinding.FragmentSearchBinding


class SearchFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
        binding.magniGlass.setOnClickListener { performSearch() }
        binding.locationPermisBtn.setOnClickListener {
            if (hasLocationPermission()) {
//                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//                    val geoCoder = Geocoder(requireContext())
//                    val currentLocation = geoCoder.getFromLocation(
//                        location.latitude,
//                        location.longitude,
//                        1
//                    )
//                    if (currentLocation != null) {
//                        Log.d("SearchFragment", currentLocation.first().adminArea)
//                        Log.d("SearchFragment", currentLocation.first().locality)
//                        Log.d("SearchFragment", currentLocation.first().subLocality)
//                        Log.d("SearchFragment", location.latitude.toString())
//                        Log.d("SearchFragment", location.longitude.toString())
//                    }
//                }
                startActivity(
                    Intent(requireActivity(), MapsActivity::class.java)
                )
            } else {
                requestLocationPermission()
            }
        }
    }

    private fun performSearch() {
        val keyword = binding.searchView.text.toString()
        val validKey = validation(keyword)
        binding.testOutput.text = validKey
    }

    private fun validation(key: String): String {
        // Pengecekan kalo inputnya kosong
        if (key.isEmpty()) {
            return "Inputan tidak boleh kosong"
        }

        // kalo inputan berupa angka (tapi lu pake string / text)
        if (key.isDigitsOnly()) {
            return "Inputan tidak boleh angka"
        }

        // kalo inputan simbol
        if (key.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            return "Inputan tidak bisa simbol"
        }

        return "Hasil Pencarian: $key"
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "Fitur ini memerlukan izin lokasi",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Izin diberikan!",
            Toast.LENGTH_SHORT
        ).show()
    }
}