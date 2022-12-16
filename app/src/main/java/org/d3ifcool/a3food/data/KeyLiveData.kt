package org.d3ifcool.a3food.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue

class KeyLiveData(private val db: DatabaseReference) : LiveData<List<Key>>()
{
    private val data = ArrayList<Key>()

    init {
        value = data.toList()
    }

    private val listener = object : ChildEventListener {
        // Dua method di bawah ini tidak kita gunakan
        override fun onCancelled(error: DatabaseError) { }
        override fun onChildMoved(snapshot: DataSnapshot, name: String?) { }

        // Tiga method di bawah ini akan kita isi nanti
        override fun onChildChanged(snapshot: DataSnapshot, name: String?) {
            val food = snapshot.getValue<Key>() ?: return
            food.id = snapshot.key ?: return
            val pos = data.indexOfFirst { it.id == food.id }
            data[pos] = food
            value = data.toList()
        }
        override fun onChildAdded(snapshot: DataSnapshot, name: String?) {
            val food = snapshot.getValue<Key>() ?: return
            food.id = snapshot.key ?: return
            data.add(food)
            value = data.toList()
        }
        override fun onChildRemoved(snapshot: DataSnapshot) {
            val id = snapshot.key ?: return
            val pos = data.indexOfFirst { it.id == id }
            data.removeAt(pos)
            value = data.toList()
        }
    }

    override fun onActive() {
        db.addChildEventListener(listener)
    }

    override fun onInactive() {
        db.removeEventListener(listener)
        data.clear()
    }
}