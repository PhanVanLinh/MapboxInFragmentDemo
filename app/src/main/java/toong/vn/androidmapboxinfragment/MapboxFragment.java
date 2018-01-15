package toong.vn.androidmapboxinfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Created by PhanVanLinh on 15/01/2018.
 * phanvanlinh.94vn@gmail.com
 */

public class MapboxFragment extends Fragment {
    private View view;
    private MapView mapView;
    private MapboxMap mapboxMap;
    String Mapbox_Key =
            "pk.eyJ1IjoicGhhbnZhbmxpbmg5NHZuIiwiYSI6ImNqMW44ZmtlbDAwcjYyd28yaDQzbzJwejAifQ.v9ID5IxcItXpaw72ZVN4dA";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), Mapbox_Key);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_mapbox, null, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.mapView);
        mapView.setStyleUrl(
                "https://www.mapion.co.jp/d/smp-apps/common/mapion-gl-stylels/style-raster-mapion-ssl.json");

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                Toast.makeText(getActivity(), "ready", Toast.LENGTH_SHORT).show();
                mapboxMap = mapboxMap;

                moveToJapan(mapboxMap);
            }
        });
    }

    /**
     * If we use Mapion key we need to move to japan to see the map
     * because the current style of mapion map not display map outside japan
     */
    private void moveToJapan(MapboxMap mapboxMap) {
        CameraPosition currentCameraPosition = mapboxMap.getCameraPosition();
        CameraPosition position = new CameraPosition.Builder(currentCameraPosition).target(
                new LatLng(35.8103467, 139.4821614, 9.79)).zoom(8).build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
