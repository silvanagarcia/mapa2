package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura la biblioteca osmdroid
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        // Obtén la MapView desde el layout
        mapView = findViewById(R.id.map);

        // Configura el mapa
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Obtén el MapController y configura el mapa
        mapController = (MapController) mapView.getController();
        mapController.setZoom(15); // Establece el nivel de zoom
        GeoPoint startPoint = new GeoPoint(-35.9150100, -64.2944800); // Coordenadas de la Torre Eiffel, por ejemplo
        mapController.setCenter(startPoint);

        // Añadir un marcador
        Marker startMarker = new Marker(mapView);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Eduardo Castex");
        mapView.getOverlays().add(startMarker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume(); // Activar la actualización del mapa
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause(); // Desactivar la actualización del mapa
    }
}