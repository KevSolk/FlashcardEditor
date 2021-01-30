 package com.example.flashcardeditor.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardeditor.R
import com.example.flashcardeditor.models.*
import com.example.flashcardeditor.utility.FlashcardDatabase
import com.example.flashcardeditor.utility.StacksListAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.collect

 class StacksMain : AppCompatActivity() {

     private var stackList: ArrayList<StackModel> = ArrayList<StackModel>()
     private var stackTagList: ArrayList<StackTagModel> = ArrayList<StackTagModel>()
     private var stackCardList: ArrayList<CardStackModel> = ArrayList<CardStackModel>()

     private lateinit var flashcardProvider: FlashcardDatabase;

     private var job: Job = Job()
     private lateinit var recyclerView: RecyclerView
     private lateinit var stacksListAdapter: StacksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stacks)
        setSupportActionBar(findViewById(R.id.toolbar))

        flashcardProvider = FlashcardDatabase.getDBInstance(this)

        // Setting up the menu bar
        val appBar = findViewById<Toolbar>(R.id.toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_drawer)
        appBar.setNavigationOnClickListener{
            drawerLayout.open()
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.close()
            true
        }

    }

     private fun refreshList(){
         CoroutineScope(job + Dispatchers.Main).launch {
             combine(
                     flashcardProvider.cardStackDAO().getAll(), // #1 Index
                     flashcardProvider.cardTagDAO().getAll(), // #2 Index
                     flashcardProvider.stackDAO().getAll() // #3 Index
             )
             { cardStacks, cardTags, stacks -> listOf(cardStacks, cardTags, stacks) }.collect {
                 flowList ->
                 val cardStacks: List<CardStackModel> = flowList[0] as List<CardStackModel>
                 val cardTags: List<CardTagModel> = flowList[1] as List<CardTagModel>
                 val stacks: List<StackModel> = flowList[2] as List<StackModel>

                 val noteListAdapterModels: ArrayList<StacksListAdapterModel> = ArrayList()

                 noteListAdapterModels.add(StacksListAdapterModel("Test Stack Please Ignore", arrayListOf("New", "Test", "Stacks Example"), 3))

                 withContext(Dispatchers.Main){
                     stacksListAdapter = StacksListAdapter(noteListAdapterModels, ::adapterOnClick)
                     recyclerView.adapter = stacksListAdapter
                 }


  /*               val notes: List<NoteInfo> = v[1] as List<NoteInfo>
                 val courses: List<CourseInfo> = v[0] as List<CourseInfo>
                 noteList = ArrayList(notes)
                 courseList = ArrayList(courses)

                 val noteListAdapter: ArrayList<NoteListAdapterModel> = ArrayList()

                 noteList.forEach{ note ->
                     val courseTitle = courseList.filter{ c->
                         c.uid == note.course
                     }[0]?.title ?: "N/A"
                     noteListAdapter.add(NoteListAdapterModel(note.text, note.title, courseTitle))
                 }

                 withContext(Dispatchers.Main){
                     listNotesAdapter = NoteListAdapter(noteListAdapter, ::adapterOnClick)
                     recyclerView.adapter = listNotesAdapter
                 }*/
             }
         }
     }

     private fun adapterOnClick(item: StacksListAdapterModel, position: Int){
         // On recycler view item click
     }
}