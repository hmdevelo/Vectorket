package com.develo.vectorket.reel.data.repository

import com.develo.vectorket.core.data.util.FAVORITES_COLLECTION
import com.develo.vectorket.core.data.util.FIELD_CREATED_AT
import com.develo.vectorket.core.data.util.FIELD_DOWNLOAD_COUNT
import com.develo.vectorket.core.data.util.FIELD_LIKE_COUNT
import com.develo.vectorket.core.data.util.FIELD_USER_ID
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.reel.domain.model.Favorite
import com.develo.vectorket.reel.domain.repository.ReelRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ReelRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    @Named(VECTORS_COLLECTION)
    private val vectors: CollectionReference,
    @Named(FAVORITES_COLLECTION)
    private val favorites: CollectionReference
) : ReelRepository {

    override fun getFavorites(userId: String) = callbackFlow {
        val snapshotListener = favorites.whereEqualTo(FIELD_USER_ID, userId)
            .orderBy(FIELD_CREATED_AT, DESCENDING)
            .addSnapshotListener { snapshot, e ->
                val favorites = snapshot?.toObjects(Favorite::class.java) ?: emptyList()
                trySend(favorites)

                e?.printStackTrace()
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addToFavorites(fav: Favorite) {
        val newFavDoc = favorites.document()
        val vectorDoc = vectors.document(fav.vectorId)

        db.runBatch { batch ->
            try {
                // Set new favorite document
                batch.set(
                    newFavDoc,
                    Favorite(
                        uid = newFavDoc.id,
                        userId = fav.userId,
                        vectorId = fav.vectorId
                    )
                )

                // Increase like count.
                batch.update(vectorDoc, FIELD_LIKE_COUNT, FieldValue.increment(1))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun removeFromFavorites(fav: Favorite) {
        val favDoc = favorites.document(fav.uid)
        val vectorDoc = vectors.document(fav.vectorId)

        db.runBatch { batch ->
            try {
                batch.delete(favDoc)

                // Decrease like count.
                batch.update(vectorDoc, FIELD_LIKE_COUNT, FieldValue.increment(-1))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun updateDownload(vector: Vector, increment: Long) {
        try {
            vectors.document(vector.uid)
                .update(FIELD_DOWNLOAD_COUNT, FieldValue.increment(increment))
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}