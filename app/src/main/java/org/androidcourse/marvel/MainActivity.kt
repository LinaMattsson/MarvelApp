package org.androidcourse.marvel

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.androidcourse.testmarvel.dto.MarvelCharacter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    CharacterFragment.OnListFragmentInteractionListener,
    ComicFragment.OnFragmentInteractionListener,
    FavoriteCharacterFragment.OnFragmentInteractionListener,
    AllComicsFragment.OnListFragmentInteractionListener,
    FavoriteComicFragment.OnListFragmentInteractionListener

{
    override fun onListFragmentInteraction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: MarvelCharacter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var charactersFragment:CharacterFragment
    lateinit var comicFragment: ComicFragment
    lateinit var favoriteCharacterFragment: FavoriteCharacterFragment
    lateinit var mainFragment: MainFragment
    lateinit var allComicsFragment: AllComicsFragment
    lateinit var favoriteComicFragment: FavoriteComicFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        charactersFragment = CharacterFragment.newInstance()
        comicFragment = ComicFragment.newInstance()
        favoriteCharacterFragment = FavoriteCharacterFragment.newInstance()
        mainFragment = MainFragment.newInstance()
        allComicsFragment = AllComicsFragment.newInstance()
        favoriteComicFragment = FavoriteComicFragment.newInstance()




        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, mainFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        nav_view.setCheckedItem(R.id.nav_home)
        
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .addToBackStack(mainFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.nav_search_character -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, charactersFragment)
                    .addToBackStack(charactersFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_search_comics -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, comicFragment)
                    .addToBackStack(comicFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_fav_characters -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, favoriteCharacterFragment)
                    .addToBackStack(favoriteCharacterFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_fav_comics -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, favoriteComicFragment)
                    .addToBackStack(favoriteComicFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.nav_all_comics -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, allComicsFragment)
                    .addToBackStack(allComicsFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
