package com.example.rathana.recyclerviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rathana.recyclerviewdemo.adapter.FilmAdapter;
import com.example.rathana.recyclerviewdemo.callback.ItemClickCallback;
import com.example.rathana.recyclerviewdemo.model.Film;
import com.hendraanggrian.widget.PaginatedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickCallback{

    private PaginatedRecyclerView rvFilm;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Film> films=new ArrayList();
    private FilmAdapter filmAdapter;

    private static final int REQUEST_CODE=1;
    private static final int REQUEST_CODE_EDIT=2;
    private static final String TAG = "MainActivity";

    private boolean isSuccess=true;
    private int totalPage=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup view
        rvFilm=findViewById(R.id.rvFilm);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        //set LayoutManager
        LinearLayoutManager layoutManager=new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        rvFilm.setLayoutManager(layoutManager);

        //create Adapter
        filmAdapter=new FilmAdapter(this,films);
        rvFilm.setAdapter(filmAdapter);
        //set pagination
        rvFilm.setPagination(paginate);

        swipeRefreshLayout.setOnRefreshListener(()->{
            updateNewData();
            itemScroll(0);
            //paginate.notifyPaginationReset();
            if(swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);

        });
        //update data
        //updateData();

    }


    PaginatedRecyclerView.Pagination paginate= new PaginatedRecyclerView.Pagination(){

        @Override
        public void onPaginate(int page) {
            new Handler().postDelayed(()->{
                updateData();
                notifyLoadingCompleted();
                },1500);

            if(page==totalPage)
                notifyPaginationFinished();
        }
    };

    private void updateData() {
        List<Film> films =new ArrayList<>();
        for(int i=0;i<15;i++){
            films.add(new Film("star war 2 "+i,"100K",R.drawable.kangaroo,"HRD"));
        }
        filmAdapter.setFilms(films);
    }

    private void updateNewData() {
        List<Film> films =new ArrayList<>();
        for(int i=0;i<15;i++){
            films.add(new Film("the meg "+i,"100K",R.drawable.kangaroo,"HRD"));
        }
        filmAdapter.setFilmsToTop(films);
    }
    /*
    * create option menu
    * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);

        MenuItem searchItem= menu.findItem(R.id.searchId);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange: "+newText);
                new Handler().postDelayed(
                        ()->{ searchFilm(newText); },1000);

                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent= new Intent(this,AddItemActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
           return true;
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO: 8/18/2018
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            Film film=data.getParcelableExtra("data");
            //set data to adapter
            filmAdapter.setFilm(film);
            itemScroll(0);
        }

        if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK){
            Film film =data.getParcelableExtra("data");
            Log.e(TAG, "onActivityResult: "+film.toString() );
            int itemPosition=data.getIntExtra("position",0);
            filmAdapter.updateFilm(film,itemPosition);
        }
    }

    public  void itemScroll(int position){
        rvFilm.smoothScrollToPosition(position);
    }

    
    @Override
    public void onLClickWithObject(Film film,int position) {
        Intent intent=new Intent(this,EditFilmActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable("data",film);
        intent.putExtra("position",position);
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUEST_CODE_EDIT);
    }

    /*public List<Film> searchFilm(String text){
        List<Film> subFilms= new ArrayList<>();
        for(Film film :films){
            if(film.getTitle().matches(text)){
                subFilms.add(film);
            }
        }
        return subFilms;
    }*/

    public void searchFilm(String text){
        if(text.isEmpty()){
            filmAdapter.clearFilms();
            updateData();
            return;
        }

        List<Film> subFilms=new ArrayList<>();
        for(Film film : this.films){
            if(film.getTitle().matches("(?i)("+text+").*")){
                subFilms.add(film);
            }
        }
        filmAdapter.replaceFilms(subFilms);


    }

}
